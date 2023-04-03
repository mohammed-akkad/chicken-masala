package com.example.chickenmasala.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAListOfRecipesOfACertainCategoryInteractor(private val dataSource: FoodDataSource<RecipeEntity>) {

    fun execute(category : String ?):List<RecipeEntity>?{

        if (category!=null)
        return dataSource.getAllItems().filter { getRecipesWithCategory(it ,category!!) }

        return null
    }
    private fun getRecipesWithCategory(recipeEntity: RecipeEntity , category: String): Boolean{
        return recipeEntity.tags.any{it.contains(category , ignoreCase = true)}&&category!=null
    }

}