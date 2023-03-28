package com.example.chickenmasala.data.domain


data class RecipeEntity(
    val name: String,
    val ingredients: List<String>,
    val totalTime: Int?,
    val cuisine: String,
    val instructions: List<String>,
    val url: String,
    val cleanedIngredients: List<String>,
    val imageUrl: String,
    val ingredientsCount: Int?,
    val tags: List<String>
)