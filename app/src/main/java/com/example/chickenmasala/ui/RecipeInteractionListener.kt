package com.example.chickenmasala.ui

import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity

interface RecipeInteractionListener {

    fun onClickItemCategories(recipeEntity: RecipeEntity)


}