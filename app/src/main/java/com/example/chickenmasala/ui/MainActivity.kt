package com.example.chickenmasala.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.ActivityMainBinding
import com.example.chickenmasala.ui.screen.game.TriviaGamesFragment
import com.example.chickenmasala.ui.screen.history.IndianFoodHistoryFragment
import com.example.chickenmasala.ui.screen.home.HomeFragment
import com.example.chickenmasala.ui.screen.kitchens.KitchenFragment
import com.example.chickenmasala.ui.screen.search.SearchFoodFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment()
    private val kitchenFragment = KitchenFragment()
    private val triviaGamesFragment = TriviaGamesFragment()
    private val indianFoodHistoryFragment = IndianFoodHistoryFragment()
    private val searchFoodFragment = SearchFoodFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addCallBacks()
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
            .addToBackStack(null)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_icon_screen -> {
                showFragment(searchFoodFragment, SEARCH_FOOD_SCREEN)
                return true
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }


    companion object {
        private const val HOME_SCREEN = "Home"
        private const val KITCHEN_SCREEN = "Kitchens"
        private const val TRIVIA_GAME_SCREEN = "Trivia Game"
        private const val ABOUT_INDIAN_FOOD_SCREEN = "About Indian Food"
        private const val SEARCH_FOOD_SCREEN = "Search"
    }
}