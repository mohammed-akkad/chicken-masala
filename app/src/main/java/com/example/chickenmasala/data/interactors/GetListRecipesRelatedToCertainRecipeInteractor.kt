package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetListRecipesRelatedToCertainRecipeInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,

    ) {
    fun execute(ingredients: String,cuisine : String,limit: Int): List<RecipeEntity>{
        return emptyList()

    }




}