package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity

class GetListRecipesRelatedToCertainRecipeInteractor(
    private val dataSource: FoodDataSource<RecipeEntity>,

    ) {
    fun execute(categories: List<String>?, cuisine: String, limit: Int): List<RecipeEntity> {
        require(limit > 0)
        return dataSource.getAllItems()
            .filter {
                when {
                    categories == null -> {
                        it.cuisine.equals(cuisine, ignoreCase = true)
                    }
                    cuisine.isEmpty() -> {
                        it.tags == categories
                    }
                    else -> {
                        it.cuisine.equals(cuisine, ignoreCase = true) && it.tags == categories
                    }
                }
            }
            .takeIf { it.isNotEmpty() }
            ?.take(limit) ?: emptyList()
    }


}




