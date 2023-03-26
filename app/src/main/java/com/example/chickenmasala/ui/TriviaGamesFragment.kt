package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.GuessTheCuisineFragment
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentTriviaGamesBinding

class TriviaGamesFragment : BaseFragment<FragmentTriviaGamesBinding>() {

    override val LOG_TAG: String = "TriviaGamesFragment"
    private val cuisineFragmentVal = GuessTheCuisineFragment()
    private val ingredientFragmentVal = GuessTheIngredientFragment()
    private val mealFragmentVal = GuessTheMealFragment()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTriviaGamesBinding =
        FragmentTriviaGamesBinding::inflate


    override fun setup() {
    }

    override fun addCallBacks() {
        binding.cardviewMiniGameOne.setOnClickListener {
            changeFragment(cuisineFragmentVal )

        }
        binding.cardviewMiniGameTwo.setOnClickListener {
            changeFragment(ingredientFragmentVal )

        }
        binding.cardviewMiniGameThree.setOnClickListener {
            changeFragment(mealFragmentVal )

        }

    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.apply{
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }


    }