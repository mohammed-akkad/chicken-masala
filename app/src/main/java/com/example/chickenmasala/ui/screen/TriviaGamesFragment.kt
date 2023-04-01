package com.example.chickenmasala.ui.screen



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.domain.GuessGamesName
import com.example.chickenmasala.databinding.FragmentTriviaGamesBinding


class TriviaGamesFragment : BaseFragment<FragmentTriviaGamesBinding>() {


    override val LOG_TAG: String = "TriviaGamesFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTriviaGamesBinding =
        FragmentTriviaGamesBinding::inflate


    override fun setup() {
    }

    override fun addCallBacks() {

        binding.guessTheIngredientCard.setOnClickListener {
            replaceFragment(GuessTheGameFragment(GuessGamesName.GUESS_THE_EXSTING_INGREDIENT))
        }
        binding.guessTheCuisineCard.setOnClickListener {
            replaceFragment(GuessTheGameFragment(GuessGamesName.GUESS_THE_CUISINE))
        }
        binding.guessTheMealCard.setOnClickListener {
            replaceFragment(GuessTheGameFragment(GuessGamesName.GUESS_THE_MEAL))
        }
    }


    private fun replaceFragment(fragment:Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

}