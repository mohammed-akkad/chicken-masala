package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentGuessGameBinding
import com.example.chickenmasala.util.Constants

class GuessTheMealFragment : BaseFragment<FragmentGuessGameBinding>() {


    override val LOG_TAG: String = "GuessTheMealFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessGameBinding =
        FragmentGuessGameBinding::inflate

    var chosenAnswer : RecipeEntity? = null
    private lateinit var randomListOfDataInteractor : GetAListOfRandomRecipesInteractor
    private lateinit var randomRecipes : List<RecipeEntity>
    private lateinit var randomRecipe : RecipeEntity

    override fun setup() {

        val recipeParser = RecipeParser()
        val dataSource = CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME,recipeParser)
        val randomListOfDataInteractor = GetAListOfRandomRecipesInteractor(dataSource)

        randomRecipes = randomListOfDataInteractor.execute(GuessTheMealFragment.NUMBER_OF_ANSWERS)
        randomRecipe = randomRecipes.random()
    }

    override fun addCallBacks() {

        prepareImage()
        prepareAnswers()
        answeringProcess()
    }
    private fun prepareImage(){

        Glide.with(this)
            .load(randomRecipe.imageUrl)
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
    private fun changeColorToYellow(answerId : Int){

        val yellowColor = ContextCompat.getColor(requireContext(), R.color.yellow_600)
        when(answerId){

            FIRST_ANSWER_KEY -> binding.answerOneText.setBackgroundColor(yellowColor)
            SECOND_ANSWER_KEY -> binding.answerTwoText.setBackgroundColor(yellowColor)
            THIRD_ANSWER_KEY -> binding.answerThreeText.setBackgroundColor(yellowColor)
            FOURTH_ANSWER_KEY -> binding.answerFourText.setBackgroundColor(yellowColor)
        }
    }
    private fun changeColorToGreen(answerId : Int){

        val greenColor = ContextCompat.getColor(requireContext(), R.color.green_100)
        when(answerId){

            FIRST_ANSWER_KEY -> binding.answerOneText.setBackgroundColor(greenColor)
            SECOND_ANSWER_KEY -> binding.answerTwoText.setBackgroundColor(greenColor)
            THIRD_ANSWER_KEY -> binding.answerThreeText.setBackgroundColor(greenColor)
            FOURTH_ANSWER_KEY -> binding.answerFourText.setBackgroundColor(greenColor)
        }
    }
    private fun changeColorToRed(answerId : Int){

        val redColor = ContextCompat.getColor(requireContext(), R.color.red_100)
        when(answerId){

            FIRST_ANSWER_KEY -> binding.answerOneText.setBackgroundColor(redColor)
            SECOND_ANSWER_KEY -> binding.answerTwoText.setBackgroundColor(redColor)
            THIRD_ANSWER_KEY -> binding.answerThreeText.setBackgroundColor(redColor)
            FOURTH_ANSWER_KEY -> binding.answerFourText.setBackgroundColor(redColor)
        }
    }
    private fun changeColorToGray(answerId : Int){

        val grayColor = ContextCompat.getColor(requireContext(), R.color.gray_200)
        when(answerId){

            FIRST_ANSWER_KEY ->{

                binding.answerFourText.setBackgroundColor(grayColor)
                binding.answerThreeText.setBackgroundColor(grayColor)
                binding.answerTwoText.setBackgroundColor(grayColor)
            }

            SECOND_ANSWER_KEY ->{

                binding.answerFourText.setBackgroundColor(grayColor)
                binding.answerThreeText.setBackgroundColor(grayColor)
                binding.answerOneText.setBackgroundColor(grayColor)
            }

            THIRD_ANSWER_KEY ->{

                binding.answerFourText.setBackgroundColor(grayColor)
                binding.answerOneText.setBackgroundColor(grayColor)
                binding.answerTwoText.setBackgroundColor(grayColor)
            }

            FOURTH_ANSWER_KEY ->{

                binding.answerOneText.setBackgroundColor(grayColor)
                binding.answerThreeText.setBackgroundColor(grayColor)
                binding.answerTwoText.setBackgroundColor(grayColor)
            }

            NUMBER_OF_ANSWERS ->{

                binding.answerOneText.setBackgroundColor(grayColor)
                binding.answerThreeText.setBackgroundColor(grayColor)
                binding.answerTwoText.setBackgroundColor(grayColor)
                binding.answerFourText.setBackgroundColor(grayColor)
            }
        }
    }
    private fun answeringProcess(){

        selectAnswer()

        binding.submitButton.setOnClickListener {

            when(chosenAnswer){

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

            answerOneText.setOnClickListener {
                changeColorToYellow(FIRST_ANSWER_KEY)
                changeColorToGray(FIRST_ANSWER_KEY)
                chosenAnswer = randomRecipes[FIRST_ANSWER_KEY]
            }

            answerTwoText.setOnClickListener {
                changeColorToYellow(SECOND_ANSWER_KEY)
                changeColorToGray(SECOND_ANSWER_KEY)
                chosenAnswer = randomRecipes[SECOND_ANSWER_KEY]
            }

            answerThreeText.setOnClickListener {
                changeColorToYellow(THIRD_ANSWER_KEY)
                changeColorToGray(THIRD_ANSWER_KEY)
                chosenAnswer = randomRecipes[THIRD_ANSWER_KEY]
            }

            answerFourText.setOnClickListener {
                changeColorToYellow(FOURTH_ANSWER_KEY)
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