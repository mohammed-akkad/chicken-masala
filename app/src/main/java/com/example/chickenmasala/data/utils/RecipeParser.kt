package com.example.chickenmasala.data.utils

import com.example.chickenmasala.data.domain.RecipeEntity

class RecipeParser : CsvParser<RecipeEntity>() {

    override fun parseLine(csvLine: String): RecipeEntity {
        val tokenizedList: List<String> = csvLine.split(",")
        return RecipeEntity(
            name = tokenizedList[RecipeCsvColumns.NAME.index],
            ingredients = tokenizedList.getItemsList(RecipeCsvColumns.INGREDIENTS.index),
            totalTime = tokenizedList.getInt(RecipeCsvColumns.TOTAL_TIME.index),
            cuisine = tokenizedList[RecipeCsvColumns.CUISINE.index],
            instructions = tokenizedList.getItemsList(RecipeCsvColumns.INSTRUCTIONS.index),
            url = tokenizedList[RecipeCsvColumns.URL.index],
            cleanedIngredients = tokenizedList.getItemsList(RecipeCsvColumns.CLEANED_INGREDIENTS.index),
            imageUrl = tokenizedList[RecipeCsvColumns.IMAGE_URL.index],
            ingredientsCount = tokenizedList.getInt(RecipeCsvColumns.INGREDIENTS_COUNT.index)
        )
    }
}
private enum class RecipeCsvColumns(val index: Int) {
    NAME(0),
    INGREDIENTS(1),
    TOTAL_TIME(2),
    CUISINE(3),
    INSTRUCTIONS(4),
    URL(5),
    CLEANED_INGREDIENTS(6),
    IMAGE_URL(7),
    INGREDIENTS_COUNT(8)
}