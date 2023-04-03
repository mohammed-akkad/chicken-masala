package com.example.chickenmasala.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAListOfSweetRecipesInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,

    ) {
    fun execute(limit : Int): List<RecipeEntity> {
        return dataSource.getAllItems()
            .filter { it.cleanedIngredients.toString().contains("sugar ",true) }
            .shuffled()
            .take(limit)
    }
}