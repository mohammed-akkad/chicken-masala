package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAListOfRandomRecipesInteractor(private val dataSource: FoodDataSource<RecipeEntity>) {

    fun execute(limit: Int): List<RecipeEntity> {
        return dataSource.getAllItems().shuffled().take(limit)
    }
}