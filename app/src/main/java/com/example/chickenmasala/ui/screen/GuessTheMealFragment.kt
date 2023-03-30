package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.GuessGamesName
import com.example.chickenmasala.data.domain.QuestionGames
import com.example.chickenmasala.data.interactors.GuessGamesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentGuessTheMealBinding
import com.example.chickenmasala.util.Constants

class GuessTheMealFragment : BaseFragment<FragmentGuessTheMealBinding>() {


    override val LOG_TAG: String = "GuessTheMealFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheMealBinding =
        FragmentGuessTheMealBinding::inflate
    private var answer : String? = null
    private var chosenAnswer : TextView? = null
    private lateinit var guessTheMeal:QuestionGames


    override fun setup() {
        val recipeParser = RecipeParser()
        val dataSource = CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME,recipeParser)
        guessTheMeal= GuessGamesInteractor(dataSource).guessGames(GuessGamesName.GUESS_THE_MEAL)
        prepareQuestion()


    }
    override fun addCallBacks() {
        prepareSelectedChoice()
        submitAnswer()
    }

    private fun submitAnswer() {
        TODO("Not yet implemented")
    }


    private fun prepareSelectedChoice() {
        val multiChoices: List<TextView> =
            listOf(binding.answerOneText,
            binding.answerTwoText,
            binding.answerThreeText,
            binding.answerFourText)
        val yellowColor = ContextCompat.getColor(requireContext(), R.color.yellow_600)
        val grayColor = ContextCompat.getColor(requireContext(), R.color.gray_200)
        for (selectedChoice in multiChoices) {
            selectedChoice.setOnClickListener {
                for (oneChoice in multiChoices) {
                    oneChoice.isSelected = (oneChoice == selectedChoice)
                    if (oneChoice.isSelected)
                        oneChoice.setBackgroundColor(yellowColor)
                    else
                        oneChoice.setBackgroundColor(grayColor)
                }
            }
        }

       }


    private fun prepareQuestion() {
        prepareImage()
        prepareMultiChoices()
    }

    private fun prepareMultiChoices() {
        binding.apply {
            answerOneText.text=guessTheMeal.correctAnswer
            answerTwoText.text=guessTheMeal.incorrectAnswers[0]
            answerThreeText.text=guessTheMeal.incorrectAnswers[1]
            answerFourText.text=guessTheMeal.incorrectAnswers[2]
        }
    }

    private fun prepareImage(){

        Glide.with(this)
            .load(guessTheMeal.question)
            .error(R.drawable.baseline_error_24)
            .into(binding.mealImageView)
    }

}