package com.example.chickenmasala.interactors

import com.example.chickenmasala.data.domain.RecipeEntity

class GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor(private val dataSource: FoodDataSource<RecipeEntity>) {

    fun execute(
        recipeName: String? = null,
        category: String?= null,
        totalTimeMins: Int? = null,
        ingredientsCount: Int?= null,
        cuisine: String?= null,
    ): List<RecipeEntity> {
        return dataSource.getAllItems().filter {
            (recipeName == null || filtersWithRecipeName(it, recipeName)) &&
                    (category == null || filtersWithCategory(it, category)) &&
                    (totalTimeMins == null || filtersWithTotalTime(it, totalTimeMins)) &&
                    (ingredientsCount == null || filtersWithIngredientsCount(it, ingredientsCount)) &&
                    (cuisine == null || filtersWithCuisine(it, cuisine))
        }
    }

    private fun filtersWithRecipeName (recipeEntity: RecipeEntity , recipeName: String) : Boolean {
        return recipeEntity.name.contains(recipeName , ignoreCase = true)
    }
    private fun filtersWithTotalTime(recipeEntity: RecipeEntity, totalTimeMins: Int): Boolean {
        return recipeEntity.totalTime?.let { it <= totalTimeMins } ?: false

    }

    private fun filtersWithIngredientsCount(recipeEntity: RecipeEntity, count: Int): Boolean {
        return recipeEntity.ingredientsCount?.let { it <= count } ?: false
    }

    private fun filtersWithCuisine(recipeEntity: RecipeEntity, cuisine: String): Boolean {
        return recipeEntity.cuisine.equals(cuisine, ignoreCase = true)
    }

    private fun filtersWithCategory(recipeEntity: RecipeEntity ,category: String ) : Boolean{
        return recipeEntity.tags.any{ it.contains(category , ignoreCase = true)}
    }

}

