package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAListOfRecipesOfACertainCategoryInteractor(private val dataSource: FoodDataSource<RecipeEntity>) {

    fun execute(category : String):List<RecipeEntity>{

        return dataSource.getAllItems().filter { getRecipesWithCategory(it ,category) }
    }
    private fun getRecipesWithCategory(recipeEntity: RecipeEntity , category: String): Boolean{
        return recipeEntity.tags.any{it.contains(category , ignoreCase = true)}
    }

}