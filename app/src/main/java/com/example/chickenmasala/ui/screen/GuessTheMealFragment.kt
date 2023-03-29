package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.chickenmasala.GuessTheCuisineFragment
import com.example.chickenmasala.GuessTheCuisineFragment.Companion.FIRST_ANSWER_KEY
import com.example.chickenmasala.GuessTheCuisineFragment.Companion.TRY_AGAIN_WORD
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.GuessGamesName
import com.example.chickenmasala.data.domain.QuestionGames
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.interactors.GuessGamesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentGuessTheMealBinding
import com.example.chickenmasala.util.Constants

class GuessTheMealFragment : BaseFragment<FragmentGuessTheMealBinding>() {


    override val LOG_TAG: String = "GuessTheMealFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheMealBinding =
        FragmentGuessTheMealBinding::inflate

    var chosenAnswer : RecipeEntity? = null
    private lateinit var guessGamesInteractor: GuessGamesInteractor
    private lateinit var guessTheMeal:QuestionGames

    override fun setup() {

        val recipeParser = RecipeParser()
        val dataSource = CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME,recipeParser)
        val randomListOfDataInteractor = GetAListOfRandomRecipesInteractor(dataSource)
        guessTheMeal=guessGamesInteractor.guessGames(GuessGamesName.GUESS_THE_MEAL)
        randomRecipes = randomListOfDataInteractor.execute(GuessTheCuisineFragment.NUMBER_OF_ANSWERS)
        randomRecipe = randomRecipes.random()
    }

    override fun addCallBacks() {

        prepareImage()
        prepareAnswers()
        answeringProcess()
    }
    private fun prepareImage(){

        Glide.with(this)
            .load(guessTheMeal.question)
            .error(R.drawable.baseline_error_24)
            .into(binding.mealImageView)
    }
    private fun prepareAnswers(){

        binding.apply {

            answerOneText.text = randomRecipes[0].name
            answerTwoText.text = randomRecipes[1].name
            answerThreeText.text = randomRecipes[2].name
            answerFourText.text = randomRecipes[3].name
        }
    }
    private fun changeColorToYellow(selectedAnswer : View){

        val yellowColor = ContextCompat.getColor(requireContext(), R.color.yellow_600)
        selectedAnswer.setBackgroundColor(yellowColor)
    }
    private fun changeColorToGreen(correctAnswer : View){

        val greenColor = ContextCompat.getColor(requireContext(), R.color.green_100)
            correctAnswer.setBackgroundColor(greenColor)
        }

    private fun changeColorToRed(wrongAnswer : View){

        val redColor = ContextCompat.getColor(wrongAnswer.context, R.color.red_100)
            wrongAnswer.setBackgroundColor(redColor)
    }
    private fun changeColorToGray(answerId : View , container:ViewGroup){

        val grayColor = ContextCompat.getColor(answerId.context, R.color.gray_200)
        for (i in 0 until container.childCount)
            if (container.getChildAt(i)!=answerId)
                answerId.setBackgroundColor(grayColor)

        }

    private fun answeringProcess(){

        selectAnswer()

        binding.submitButton.setOnClickListener {

            when(guessTheMeal.correctAnswer){

                randomRecipes[FIRST_ANSWER_KEY] -> {

                    checkIfRightAnswer(FIRST_ANSWER_KEY)
                    disableClickingForAnswers()
                    repeat()
                }

                randomRecipes[SECOND_ANSWER_KEY] -> {

                    checkIfRightAnswer(SECOND_ANSWER_KEY)
                    disableClickingForAnswers()
                    repeat()
                }

                randomRecipes[THIRD_ANSWER_KEY] -> {

                    checkIfRightAnswer(THIRD_ANSWER_KEY)
                    disableClickingForAnswers()
                    repeat()
                }

                randomRecipes[FOURTH_ANSWER_KEY] -> {

                    checkIfRightAnswer(FOURTH_ANSWER_KEY)
                    disableClickingForAnswers()
                    repeat()
                }

                null ->{

                    Toast.makeText(requireContext(), "Select an answer please", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun checkIfRightAnswer(answerId : Int){

        if (chosenAnswer == randomRecipe){

            changeColorToGreen(answerId)
            changeButtonTextToNext()
        }

        else{

            changeColorToRed(answerId)
            changeButtonTextToTryAgain()
        }

    }
    private fun changeButtonTextToNext(){

        binding.submitButton.text = NEXT_WORD
    }
    private fun changeButtonTextToSubmit(){

        binding.submitButton.text = SUBMIT_WORD
    }
    private fun changeButtonTextToTryAgain(){

        binding.submitButton.text = TRY_AGAIN_WORD
    }
    private fun selectAnswer(){

        binding.apply {

            answerOne.setOnClickListener {
                changeColorToYellow(answerOne)
                changeColorToGray(answer)
                chosenAnswer = randomRecipes[FIRST_ANSWER_KEY]
            }

            answerTwo.setOnClickListener {
                changeColorToYellow(answerTwo)
                changeColorToGray(SECOND_ANSWER_KEY)
                chosenAnswer = randomRecipes[SECOND_ANSWER_KEY]
            }

            answerThree.setOnClickListener {
                changeColorToYellow(answerThree)
                changeColorToGray(THIRD_ANSWER_KEY)
                chosenAnswer = randomRecipes[THIRD_ANSWER_KEY]
            }

            answerFour.setOnClickListener {
                changeColorToYellow(answerFour)
                changeColorToGray(FOURTH_ANSWER_KEY)
                chosenAnswer = randomRecipes[FOURTH_ANSWER_KEY]
            }
        }
    }
    private fun repeat(){

        if (binding.submitButton.text != SUBMIT_WORD){

            binding.submitButton.setOnClickListener {

                chosenAnswer = null
                setup()
                addCallBacks()
                changeButtonTextToSubmit()
                changeColorToGray(4)
            }
        }
    }
    private fun disableClickingForAnswers(){

        binding.apply {

            answerOneText.isClickable = false
            answerTwoText.isClickable = false
            answerThreeText.isClickable = false
            answerFourText.isClickable = false
        }
    }

    companion object{

        const val NUMBER_OF_ANSWERS = 4
        const val FOURTH_ANSWER_KEY = 3
        const val THIRD_ANSWER_KEY = 2
        const val SECOND_ANSWER_KEY = 1
        const val FIRST_ANSWER_KEY = 0
        const val SUBMIT_WORD = "Submit"
        const val NEXT_WORD = "Next"
        const val TRY_AGAIN_WORD = "Try Again"
    }
}