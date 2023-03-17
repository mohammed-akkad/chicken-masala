package com.example.chickenmasala.data.utils

import com.example.chickenmasala.data.domain.RecipeEntity

class CsvParser {

    fun parseLine(csvLine: String): RecipeEntity {
        val tokenizedList: List<String> = csvLine.split(",")
        return RecipeEntity(
            name = tokenizedList[Constants.ColumnIndex.NAME],
            ingredients = tokenizedList.getItemsList(Constants.ColumnIndex.INGREDIENTS),
            totalTime = tokenizedList.getInt(Constants.ColumnIndex.TOTAL_TIME),
            cuisine = tokenizedList[Constants.ColumnIndex.CUISINE],
            instructions = tokenizedList.getItemsList(Constants.ColumnIndex.INSTRUCTIONS),
            url = tokenizedList[Constants.ColumnIndex.URL],
            cleanedIngredients = tokenizedList.getItemsList(Constants.ColumnIndex.CLEANED_INGREDIENTS),
            imageUrl = tokenizedList[Constants.ColumnIndex.IMAGE_URL],
            ingredientsCount = tokenizedList.getInt(Constants.ColumnIndex.INGREDIENTS_COUNT)
        )
    }

    private fun List<String>.getItemsList(index: Int): List<String> {
        return this[index].split(";")
    }

    private fun List<String>.getInt(index: Int): Int? {
        return this[index].toIntOrNull()
    }
}