package com.example.chickenmasala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.interactors.GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.ActivityMainBinding
import com.example.chickenmasala.util.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val TAG = "MAIN_ACTIVITY"

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(FoodDetailsFragment()) // Home Fragment

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_screen -> replaceFragment(FoodKitchenCategoryFragment()) // Home Fragment
                R.id.kitchen_screen -> replaceFragment(FoodDetailsFragment()) // Kitchen Fragment
                R.id.trivia_screen -> replaceFragment(TriviaGamesFragment())
                R.id.indian_food_history -> replaceFragment(GuessTheCuisineFragment())

                else ->{

                }
            }
            true
        }


        }

    private fun replaceFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container , fragment)
        fragmentTransaction.commit()

    }
}