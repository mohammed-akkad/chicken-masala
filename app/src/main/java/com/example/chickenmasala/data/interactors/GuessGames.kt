package com.example.chickenmasala.data.interactors


import com.example.chickenmasala.data.domain.GuessGamesName
import com.example.chickenmasala.data.domain.QuestionGames
import com.example.chickenmasala.data.domain.RecipeEntity

class GuessGames(private val dataSource: FoodDataSource<RecipeEntity>) {
    private  val randomRecipe: RecipeEntity = getRandomRecipe()
    fun guessGames(gameName: GuessGamesName): QuestionGames {
        return when (gameName) {
              GuessGamesName.GUESS_THE_CUISINE ->guessCuisine()
            GuessGamesName.GUESS_THE_EXSTING_INGREDIENT->guessExstingIngredient()
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
        TODO("Not yet implemented")
    }

    private fun guessExstingIngredient(): QuestionGames {
        TODO("Not yet implemented")
    }

    private fun getRandomRecipe() = dataSource.getAllItems().random()
}