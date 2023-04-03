package com.example.chickenmasala.data

import android.content.Context
import com.example.chickenmasala.interactors.FoodDataSource
import com.example.chickenmasala.data.utils.CsvParser
import java.io.BufferedReader
import java.io.InputStreamReader

class CsvDataSource<T>(
    private val context: Context,
    private val fileName: String,
    private val parser: CsvParser<T>,
) : FoodDataSource<T> {
    override fun getAllItems(): List<T> {
        val list = mutableListOf<T>()
        val inputStream = context.assets.open(fileName)
        val buffer = BufferedReader(InputStreamReader(inputStream))
        buffer.forEachLine { line ->
            list.add(parser.parseLine(line))
        }
        return list
    }

}

