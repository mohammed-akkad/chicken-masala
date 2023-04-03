package com.example.chickenmasala.data.interactors

import com.example.chickenmasala.data.domain.CategoryEntity

class GetAListOfCategoryInteractor(
    private val dataSource: FoodDataSource<CategoryEntity>,
    ) {
    fun execute(): List<CategoryEntity> {
        return dataSource.getAllItems()
            .shuffled()
            .take(5)
    }
}