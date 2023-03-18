package com.example.chickenmasala.data.utils


abstract class CsvParser <T> {

    abstract fun parseLine(csvLine: String): T

    fun List<String>.getItemsList(index: Int): List<String> {
        return this[index].split(";")
    }

    fun List<String>.getInt(index: Int): Int? {
        return this[index].toIntOrNull()
    }
}