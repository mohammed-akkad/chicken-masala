package com.example.chickenmasala.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.FoodDataSource
import com.example.chickenmasala.data.interactors.GetAllCuisineImageUrlsAndNamesInteractor
import com.example.chickenmasala.data.utils.CsvParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.ActivityMainBinding
import com.example.chickenmasala.util.Operation

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val kitchenFragment = KitchenFragment()
    private val triviaGamesFragment = TriviaGamesFragment()
    private val indianFoodHistoryFragment = IndianFoodHistoryFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
        addCallBacks()


    }

    private fun setup() {

    }

    private fun addCallBacks() {

        showFragment(homeFragment, HOME_SCREEN)
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home_screen -> {
                    showFragment(homeFragment, HOME_SCREEN)
                    true
                }
                R.id.kitchen_screen -> {
                    showFragment(kitchenFragment, KITCHEN_SCREEN)
                    true
                }
                R.id.trivia_screen -> {
                    showFragment(triviaGamesFragment, TRIVIA_GAME_SCREEN)
                    true
                }
                R.id.indian_food_history -> {
                    showFragment(indianFoodHistoryFragment, ABOUT_INDIAN_FOOD_SCREEN)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment, screenTitle: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()

        title = screenTitle

    }

    private fun updateSelectedItemId(itemId: Int) {
        binding.bottomNavigation.selectedItemId = itemId
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val selected = binding.bottomNavigation.selectedItemId
        updateSelectedItemId(selected)
    }

    companion object {
        private val LOG_TAG = "MAIN_ACTIVITY"
        private val HOME_SCREEN = "Home"
        private val KITCHEN_SCREEN = "Kitchens"
        private val TRIVIA_GAME_SCREEN = "Trivia Game"
        private val ABOUT_INDIAN_FOOD_SCREEN = "About Indian Food"
    }

}