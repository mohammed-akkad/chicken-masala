package com.example.chickenmasala.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAllCuisineImageUrlsAndNamesInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,


    ) {
    fun execute(): List<RecipeEntity> {
        return dataSource.getAllItems().shuffled().distinctBy {
            it.cuisine
        }
    }

}
