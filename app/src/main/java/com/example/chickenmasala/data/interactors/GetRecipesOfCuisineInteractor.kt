package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetRecipesOfCuisineInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,
) {
    fun execute(cuisine: String, limit: Int): List<RecipeEntity> {
        require(limit>0)
        return dataSource.getAllItems()
            .filter { it.cuisine.equals(cuisine, ignoreCase = true) }
            .shuffled()
            .let { recipesList ->
                if (limit > 0) recipesList.take(limit) else recipesList
            }
    }
}