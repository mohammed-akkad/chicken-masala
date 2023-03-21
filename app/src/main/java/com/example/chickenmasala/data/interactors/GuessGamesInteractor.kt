package com.example.chickenmasala.data.interactors


import com.example.chickenmasala.data.domain.GuessGamesName
import com.example.chickenmasala.data.domain.QuestionGames
import com.example.chickenmasala.data.domain.RecipeEntity

class GuessGamesInteractor(private val dataSource: FoodDataSource<RecipeEntity>) {
    private  val randomRecipe: RecipeEntity = getRandomRecipe()
    fun guessGames(gameName: GuessGamesName): QuestionGames {
        return when (gameName) {
              GuessGamesName.GUESS_THE_CUISINE ->guessCuisine()
            GuessGamesName.GUESS_THE_EXSTING_INGREDIENT->guessExistingIngredient()
            GuessGamesName.GUESS_THE_MEAL -> guessMeal()
        }

    }


    private fun guessMeal(): QuestionGames {
        val correctName=randomRecipe.name
        val correctIngredent=randomRecipe.cleanedIngredients.random()
        val result=dataSource.getAllItems()
            .filterNot { it.cleanedIngredients.contains(correctIngredent) }
        return QuestionGames(
            correctName,
            correctIngredent, listOf(
                result[0].cleanedIngredients.random(),
                result[1].cleanedIngredients.random(),
                result[2].cleanedIngredients.random()
            )
        )
    }

    private fun guessCuisine(): QuestionGames {
        val correctCuisine=randomRecipe.cuisine
        val correctRecipeName=randomRecipe.name
        val result=dataSource.getAllItems()
            .filterNot { it.name.contains(correctRecipeName) }
        return QuestionGames(
            correctRecipeName,
            correctCuisine, listOf(
                result[0].cuisine, result[1].cuisine, result[2].cuisine)
        )
    }

    private fun guessExistingIngredient(): QuestionGames {
        val correctIngriedent = randomRecipe.cleanedIngredients.random()
        val result = dataSource.getAllItems()
            .filterNot { it.cleanedIngredients.contains(correctIngriedent) && it.cleanedIngredients.size < 3 }
            .random()
        return QuestionGames(
            result.imageUrl, correctIngriedent, listOf(
                result.cleanedIngredients[0],
                result.cleanedIngredients[1],
                result.cleanedIngredients[2]
            )
        )
    }

    private fun getRandomRecipe() = dataSource.getAllItems().random()
}