package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.chickenmasala.GuessTheCuisineFragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.utils.CsvParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentGuessTheIngredientBinding
import com.example.chickenmasala.util.Constants


class GuessTheIngredientFragment : BaseFragment<FragmentGuessTheIngredientBinding>() {


    override val LOG_TAG: String = "GuessTheIngredientFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheIngredientBinding = FragmentGuessTheIngredientBinding::inflate

    private var chosenAnswer : RecipeEntity? = null
    private lateinit var randomListOfDataInteractor : GetAListOfRandomRecipesInteractor
    private lateinit var randomRecipes : List<RecipeEntity>
    private lateinit var randomRecipe : RecipeEntity

    override fun setup() {

        val recipeParser = RecipeParser()
        val dataSource = CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME,recipeParser)
        val randomListOfDataInteractor = GetAListOfRandomRecipesInteractor(dataSource)

        randomRecipes = randomListOfDataInteractor.execute(GuessTheCuisineFragment.NUMBER_OF_ANSWERS)
        randomRecipe = randomRecipes.random()
    }

    override fun addCallBacks() {

        prepareQuestion()
        prepareAnswers()
        answeringProcess()
    }
    private fun prepareQuestion(){

        val questionText = "What is the valid ingredient in " + "${randomRecipe.name} ? "
        binding.guessTheValidIngredientQuestion.text = questionText
    }
    private fun prepareAnswers(){

        binding.apply {

            answerOneText.text = "1 - ${randomRecipes[0].ingredients[0]}"
            answerTwoText.text = "2 - ${randomRecipes[1].ingredients[0]}"
            answerThreeText.text = "3 - ${randomRecipes[2].ingredients[0]}"
            answerFourText.text = "4 - ${randomRecipes[3].ingredients[0]}"
        }
    }
    private fun changeColorToYellow(answerId : Int){

        val yellowColor = ContextCompat.getColor(requireContext(), R.color.yellow_600)
        when(answerId){

            FIRST_ANSWER_KEY -> binding.constraintLayoutOfCard1.setBackgroundColor(yellowColor)
            SECOND_ANSWER_KEY -> binding.constraintLayoutOfCard2.setBackgroundColor(yellowColor)
            THIRD_ANSWER_KEY -> binding.constraintLayoutOfCard3.setBackgroundColor(yellowColor)
            FOURTH_ANSWER_KEY -> binding.constraintLayoutOfCard4.setBackgroundColor(yellowColor)
        }
    }
    private fun changeColorToGreen(answerId : Int){

        val greenColor = ContextCompat.getColor(requireContext(), R.color.green_100)
        when(answerId){

            FIRST_ANSWER_KEY -> binding.constraintLayoutOfCard1.setBackgroundColor(greenColor)
            SECOND_ANSWER_KEY -> binding.constraintLayoutOfCard2.setBackgroundColor(greenColor)
            THIRD_ANSWER_KEY -> binding.constraintLayoutOfCard3.setBackgroundColor(greenColor)
            FOURTH_ANSWER_KEY -> binding.constraintLayoutOfCard4.setBackgroundColor(greenColor)
        }
    }
    private fun changeColorToRed(answerId : Int){

        val redColor = ContextCompat.getColor(requireContext(), R.color.red_100)
        when(answerId){

            FIRST_ANSWER_KEY -> binding.constraintLayoutOfCard1.setBackgroundColor(redColor)
            SECOND_ANSWER_KEY -> binding.constraintLayoutOfCard2.setBackgroundColor(redColor)
            THIRD_ANSWER_KEY -> binding.constraintLayoutOfCard3.setBackgroundColor(redColor)
            FOURTH_ANSWER_KEY -> binding.constraintLayoutOfCard4.setBackgroundColor(redColor)
        }
    }
    private fun changeColorToGray(answerId : Int){

        val grayColor = ContextCompat.getColor(requireContext(), R.color.gray_200)
        when(answerId){

            FIRST_ANSWER_KEY ->{

                binding.constraintLayoutOfCard4.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard3.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard2.setBackgroundColor(grayColor)
            }

            SECOND_ANSWER_KEY ->{

                binding.constraintLayoutOfCard4.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard3.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard1.setBackgroundColor(grayColor)
            }

            THIRD_ANSWER_KEY ->{

                binding.constraintLayoutOfCard4.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard1.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard2.setBackgroundColor(grayColor)
            }

            FOURTH_ANSWER_KEY ->{

                binding.constraintLayoutOfCard1.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard3.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard2.setBackgroundColor(grayColor)
            }

            NUMBER_OF_ANSWERS ->{

                binding.constraintLayoutOfCard1.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard3.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard2.setBackgroundColor(grayColor)
                binding.constraintLayoutOfCard4.setBackgroundColor(grayColor)
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

            answerOne.setOnClickListener {
                changeColorToYellow(FIRST_ANSWER_KEY)
                changeColorToGray(FIRST_ANSWER_KEY)
                chosenAnswer = randomRecipes[FIRST_ANSWER_KEY]
            }

            answerTwo.setOnClickListener {
                changeColorToYellow(SECOND_ANSWER_KEY)
                changeColorToGray(SECOND_ANSWER_KEY)
                chosenAnswer = randomRecipes[SECOND_ANSWER_KEY]
            }

            answerThree.setOnClickListener {
                changeColorToYellow(THIRD_ANSWER_KEY)
                changeColorToGray(THIRD_ANSWER_KEY)
                chosenAnswer = randomRecipes[THIRD_ANSWER_KEY]
            }

            answerFour.setOnClickListener {
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

            answerOne.isClickable = false
            answerTwo.isClickable = false
            answerThree.isClickable = false
            answerFour.isClickable = false
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