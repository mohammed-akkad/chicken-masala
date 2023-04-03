package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.GuessGamesName
import com.example.chickenmasala.databinding.FragmentTriviaGamesBinding

class TriviaGamesFragment : BaseFragment<FragmentTriviaGamesBinding>() {

    override val LOG_TAG: String = TriviaGamesFragment::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTriviaGamesBinding =
        FragmentTriviaGamesBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {
        binding.guessTheIngredientCard.setOnClickListener {
            replaceFragment(GuessTheGameFragment(GuessGamesName.GUESS_THE_EXSTING_INGREDIENT),getString(R.string.Chicken))
        }
        binding.guessTheCuisineCard.setOnClickListener {
            replaceFragment(GuessTheGameFragment(GuessGamesName.GUESS_THE_CUISINE),getString(R.string.guess_the_cuisine))
        }
        binding.guessTheMealCard.setOnClickListener {
            replaceFragment(GuessTheGameFragment(GuessGamesName.GUESS_THE_MEAL), getString(R.string.guess_the_meal))
        }
    }

    private fun replaceFragment(fragment: Fragment, title : String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
        setTitleAppBar(title)
    }

    private fun setTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name "
        }
    }
}