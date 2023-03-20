package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAllCuisineImageUrlsAndNamesInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,


    ) {
    fun execute(cuisine: String, imageUrl: String) : List<RecipeEntity>{
       return emptyList()

    }
}