package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetRecipesOfCuisineInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,
) {
    fun execute(cuisine: String, limit: Int): List<RecipeEntity> {
        return emptyList()
    }
}