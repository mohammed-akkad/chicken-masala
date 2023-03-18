package com.example.chickenmasala.data.interactors

interface FoodDataSource<T> {
    fun getAllItems(): List<T>
}