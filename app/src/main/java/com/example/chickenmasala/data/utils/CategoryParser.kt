package com.example.chickenmasala.data.utils

import com.example.chickenmasala.data.domain.CategoryEntity

class CategoryParser : CsvParser<CategoryEntity>() {

    override fun parseLine(csvLine: String): CategoryEntity {
        val tokenizedList: List<String> = csvLine.split(",")
        return CategoryEntity(
            name = tokenizedList[Constants.CategoryFileColumnIndex.NAME],
            description = tokenizedList[Constants.CategoryFileColumnIndex.DESCRIPTION].replace(";", ", "),
            imageUrl = tokenizedList[Constants.CategoryFileColumnIndex.IMAGE_URL],
        )
    }
}