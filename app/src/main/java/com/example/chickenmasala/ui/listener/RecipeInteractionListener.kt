package com.example.chickenmasala.ui.listener

import com.example.chickenmasala.data.domain.RecipeEntity

interface RecipeInteractionListener {
    fun onClickItemRecipeEntitty(recipeEntity: RecipeEntity)
}