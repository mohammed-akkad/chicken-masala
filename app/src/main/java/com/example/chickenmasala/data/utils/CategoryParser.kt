package com.example.chickenmasala.data.utils

import com.example.chickenmasala.data.domain.CategoryEntity

class CategoryParser : CsvParser<CategoryEntity>() {

    override fun parseLine(csvLine: String): CategoryEntity {
        val tokenizedList: List<String> = csvLine.split(",")
        return CategoryEntity(
            name = tokenizedList[CategoryCsvColumns.NAME.index],
            description = tokenizedList[CategoryCsvColumns.DESCRIPTION.index].replace(";", ", "),
            imageUrl = tokenizedList[CategoryCsvColumns.IMAGE_URL.index],
        )
    }
}
private enum class CategoryCsvColumns(val index: Int) {
    NAME(0),
    DESCRIPTION(1),
    IMAGE_URL(2)
}