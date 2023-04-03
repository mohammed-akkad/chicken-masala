package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAListOfCakeRecipesInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,

    ) {
    fun execute(): List<RecipeEntity> {
        return dataSource.getAllItems()
            .filter { it.cleanedIngredients.toString().contains("Cake ") }.shuffled()
            .take(5)
    }
}