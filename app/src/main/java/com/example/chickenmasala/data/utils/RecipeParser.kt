package com.example.chickenmasala.data.utils

import com.example.chickenmasala.data.domain.RecipeEntity

class RecipeParser : CsvParser<RecipeEntity>() {

    override fun parseLine(csvLine: String): RecipeEntity {
        val tokenizedList: List<String> = csvLine.split(",")
        return RecipeEntity(
            name = tokenizedList[Constants.RecipeFileColumnIndex.NAME],
            ingredients = tokenizedList.getItemsList(Constants.RecipeFileColumnIndex.INGREDIENTS),
            totalTime = tokenizedList.getInt(Constants.RecipeFileColumnIndex.TOTAL_TIME),
            cuisine = tokenizedList[Constants.RecipeFileColumnIndex.CUISINE],
            instructions = tokenizedList.getItemsList(Constants.RecipeFileColumnIndex.INSTRUCTIONS),
            url = tokenizedList[Constants.RecipeFileColumnIndex.URL],
            cleanedIngredients = tokenizedList.getItemsList(Constants.RecipeFileColumnIndex.CLEANED_INGREDIENTS),
            imageUrl = tokenizedList[Constants.RecipeFileColumnIndex.IMAGE_URL],
            ingredientsCount = tokenizedList.getInt(Constants.RecipeFileColumnIndex.INGREDIENTS_COUNT)
        )
    }
}