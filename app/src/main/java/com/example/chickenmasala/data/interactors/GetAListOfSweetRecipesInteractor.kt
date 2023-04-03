package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAListOfSweetRecipesInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,

    ) {
    fun execute(): List<RecipeEntity> {
        return dataSource.getAllItems()
            .filter { it.cleanedIngredients.toString().contains("sugar ") }
            .shuffled()
            .take(5)
    }
}