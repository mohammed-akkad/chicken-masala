package com.example.chickenmasala.data.interactors


import com.example.chickenmasala.data.domain.GuessGamesName
import com.example.chickenmasala.data.domain.QuestionGames
import com.example.chickenmasala.data.domain.RecipeEntity

class GuessGamesInteractor(private val dataSource: FoodDataSource<RecipeEntity>) {
    private val randomRecipe: RecipeEntity = getRandomRecipe()
    fun guessGames(gameName: GuessGamesName): QuestionGames {
        return when (gameName) {
            GuessGamesName.GUESS_THE_CUISINE -> guessCuisine()
            GuessGamesName.GUESS_THE_EXSTING_INGREDIENT -> guessExistingIngredient()
            GuessGamesName.GUESS_THE_MEAL -> guessMeal()
        }
    }

    private fun guessExistingIngredient(): QuestionGames {
        val correctName = randomRecipe.name
        val correctIngredent = randomRecipe.cleanedIngredients.random()
        val wrongAnswersRecipe = dataSource.getAllItems()
            .filterNot { it.cleanedIngredients.contains(correctIngredent) }
        val wrongAnswers = listOf(
            wrongAnswersRecipe[0].cleanedIngredients.random(),
            wrongAnswersRecipe[1].cleanedIngredients.random(),
            wrongAnswersRecipe[2].cleanedIngredients.random(),
        )
        return question(correctName, correctIngredent, wrongAnswers)
    }

    private fun guessCuisine(): QuestionGames {
        val correctCuisine = randomRecipe.cuisine
        val correctRecipeName = randomRecipe.name
        val wrongAnswersRecipe = dataSource.getAllItems()
            .filterNot { it.name.contains(correctRecipeName) }
        val wrongAnswers = listOf(
            wrongAnswersRecipe[0].cuisine,
            wrongAnswersRecipe[1].cuisine,
            wrongAnswersRecipe[2].cuisine
        )
        return question(correctRecipeName, correctCuisine, wrongAnswers)
    }

    private fun guessMeal(): QuestionGames {
        val imageUrl = randomRecipe.imageUrl
        val correctAnswer = randomRecipe.name
        val wrongAnswersRecipe = dataSource.getAllItems()
            .filterNot { it.imageUrl == imageUrl }
        val wrongAnswers = listOf(
            wrongAnswersRecipe[0].name,
            wrongAnswersRecipe[1].name,
            wrongAnswersRecipe[2].name
        )
        return question(imageUrl, correctAnswer, wrongAnswers)
    }

    private fun question(
        correctName: String,
        correctIngredent: String,
        wrongAnswers: List<String>,
    ): QuestionGames {
        return QuestionGames(
            correctName,
            correctIngredent,
            wrongAnswers[0],
            wrongAnswers[1],
            wrongAnswers[2]
        )
    }

    private fun getRandomRecipe() = dataSource.getAllItems().random()
}