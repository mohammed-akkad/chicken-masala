package com.example.chickenmasala.ui.screen



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.GuessTheCuisineFragment
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentTriviaGamesBinding


class TriviaGamesFragment : BaseFragment<FragmentTriviaGamesBinding>() {

    val cuisineFragmentVal = GuessTheCuisineFragment()
    val ingredientFragmentVal = GuessTheIngredientFragment()
    val mealFragmentVal = GuessTheMealFragment()

    override val LOG_TAG: String = "TriviaGamesFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTriviaGamesBinding =
        FragmentTriviaGamesBinding::inflate


    override fun setup() {
    }

    override fun addCallBacks() {

        binding.cardviewMiniGameOne.setOnClickListener {
            replaceFragment(GuessTheIngredientFragment())
        }
        binding.cardviewMiniGameTwo.setOnClickListener {
            replaceFragment(GuessTheCuisineFragment())
        }
        binding.cardviewMiniGameThree.setOnClickListener {
            replaceFragment(GuessTheMealFragment())
        }
    }
    private fun replaceFragment(fragment:Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

}