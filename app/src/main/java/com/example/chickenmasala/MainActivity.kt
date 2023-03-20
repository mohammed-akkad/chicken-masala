package com.example.chickenmasala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.interactors.GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.util.Constants

class MainActivity : AppCompatActivity() {
    private val TAG = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        //Just for testing and must be removed
//        CsvDataSource(this, Constants.RECIPES_CSV_FILE_NAME, RecipeParser()).apply {
//            getAllItems().forEachIndexed { index, recipeEntity ->
//                Log.v(TAG, "[$index]: $recipeEntity")
//            }
//        }

//        //Just for testing and must be removed [demonstration of calling an interactor]
//        CsvDataSource(this, Constants.RECIPES_CSV_FILE_NAME, RecipeParser()).apply {
//            GetRecipesOfCuisineInteractor(this).execute("indian",5).forEachIndexed { index, recipeEntity ->
//                Log.v(TAG, "[$index]: $recipeEntity")
//            }
//        }

        CsvDataSource(this , Constants.RECIPES_CSV_FILE_NAME , RecipeParser()).apply {
            GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor(this).execute(
                ingredientsCount = 10
            )    .forEachIndexed{ index , recipeEntity ->
                    Log.v("iii" , "[${index+1}] "
//                            + "Name :  ${recipeEntity.name}"
//                            + " ,Time :  ${recipeEntity.totalTime} ,"
                              + " Count :  ${recipeEntity.ingredientsCount} "
//                            + " , Cuisine : ${recipeEntity.cuisine}"
//                            + " , Tag ${recipeEntity.tags}"
                    )
                }
        }
//        CsvDataSource(this, Constants.RECIPES_CSV_FILE_NAME, RecipeParser()).apply {
//            GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor(this).execute(
//                cuisine = "indian" ,
//                totalTimeMins = 10 ,
////                ingredientsCount = 10,
//
//            )
//                .forEachIndexed{ index , recipeEntity ->
//                    Log.v("iii" , "[${index+1}] "
////                            + "Name :  ${recipeEntity.name}"
//                            + " ,Time :  ${recipeEntity.totalTime} ,"
//                            + " Count :  ${recipeEntity.ingredientsCount} "
//                            + " , Cuisine : ${recipeEntity.cuisine}"
//                    )
//                }
//        }



//        ////Just for testing and must be removed
//        CsvDataSource(this, Constants.CATEGORIES_CSV_FILE_NAME, CategoryParser()).apply {
//            getAllItems().forEachIndexed { index, categoryEntity ->
//                Log.v(TAG, "[$index]: $categoryEntity")
//            }
//        }


    }
}