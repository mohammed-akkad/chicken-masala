package com.example.chickenmasala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chickenmasala.data.DataManager
import com.example.chickenmasala.data.utils.CsvParser
import com.example.chickenmasala.util.Constants
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private val TAG = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //For Testing
        parseCsvFile()
        DataManager.allRecipesList.forEachIndexed { index, recipeEntity ->
            Log.v(TAG, "[$index]: $recipeEntity")
        }
    }

    private fun parseCsvFile() {
        val inputStream = assets.open(Constants.CSV_FILE_NAME)
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
            DataManager.allRecipesList.add(parser.parseLine(it))
        }
    }
}