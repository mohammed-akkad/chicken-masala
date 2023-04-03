package com.example.chickenmasala.interactors

interface FoodDataSource<T> {
    fun getAllItems(): List<T>
}