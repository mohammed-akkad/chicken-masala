package com.example.chickenmasala.data.interactors


import com.example.chickenmasala.data.domain.GuessGamesName
import com.example.chickenmasala.data.domain.QuestionGames
import com.example.chickenmasala.data.domain.RecipeEntity

class GuessGames(private val dataSource: FoodDataSource<RecipeEntity>) {

    fun guessGames(gameName: GuessGamesName): QuestionGames {
        return when (gameName) {
              GuessGamesName.GUESS_THE_CUISINE ->guessCuisine()
            GuessGamesName.GUESS_THE_EXSTING_INGREDIENT->guessExstingIngredient()
            GuessGamesName.GUESS_THE_MEAL -> guessMeal()
        }

    }


    private fun guessMeal(): QuestionGames {
        TODO("Not yet implemented")
    }

    private fun guessCuisine(): QuestionGames {
        TODO("Not yet implemented")
    }

    private fun guessExstingIngredient(): QuestionGames {
        TODO("Not yet implemented")
    }



}