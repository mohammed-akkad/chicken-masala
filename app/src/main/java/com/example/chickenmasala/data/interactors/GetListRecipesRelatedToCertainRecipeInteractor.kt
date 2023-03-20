package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetListRecipesRelatedToCertainRecipeInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,

    ) {
    fun execute(ingredients: List<String>,cuisine : String,limit: Int): List<RecipeEntity> {


        return dataSource.getAllItems()
            .filter { (it.cuisine.equals(cuisine, ignoreCase = true)) || (it.ingredients == ingredients) }
            .let {
                if(limit > 0) it.take(limit) else throw Exception("Please kindly specify the limit correctly")
            }

    }




}