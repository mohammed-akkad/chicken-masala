package com.example.chickenmasala

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.utils.CsvParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentGuessTheCuisineBinding


class GuessTheCuisineFragment : BaseFragment<FragmentGuessTheCuisineBinding>() {

    private lateinit var dataSource: CsvDataSource<RecipeEntity>
    private lateinit var randomListOfData : GetAListOfRandomRecipesInteractor
    private lateinit var recipeParser: RecipeParser

    var randomRecipes = randomListOfData.execute(4)
    var randomRecipe = randomRecipes.random()
    override val LOG_TAG: String = "GuessTheCuisineFrag"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheCuisineBinding =
        FragmentGuessTheCuisineBinding::inflate

    override fun setup() {

        recipeParser = RecipeParser()
        dataSource = CsvDataSource(requireContext(),"indian_food.csv",recipeParser)
        randomListOfData = GetAListOfRandomRecipesInteractor(dataSource)
    }
    override fun addCallBacks() {

        prepareQuestion()
        prepareAnswers()
        answeringProcess()
    }
    private fun prepareQuestion(){

        val questionText = "To which cuisine , " + "${randomRecipe.name} " + "belong ? "
        binding.guessTheCuisineQuestion.text = questionText
    }
    private fun answeringProcess(){

        val chosenAnswer = selectAnswer()

        binding.submitButton.setOnClickListener {

            when(chosenAnswer){

                randomRecipes[0] -> {

                    checkIfRightAnswer(chosenAnswer,0)
                }

                randomRecipes[1] -> {

                    checkIfRightAnswer(chosenAnswer,1)
                }

                randomRecipes[2] -> {

                    checkIfRightAnswer(chosenAnswer,2)
                }

                randomRecipes[3] -> {

                    checkIfRightAnswer(chosenAnswer,3)
                }

                null ->{

                    Toast.makeText(requireContext(), "Select an answer please", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun checkIfRightAnswer(chosenAnswer : RecipeEntity, answerId : Int){

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

        binding.submitButton.text = "Next"
    }
    private fun changeButtonTextToTryAgain(){

        binding.submitButton.text = "Try Again"
    }
    private fun prepareAnswers(){

        binding.apply {

            firstAnswer.text = "1 - ${randomRecipes[0].cuisine}"
            secondAnswer.text = "2 - ${randomRecipes[1].cuisine}"
            thirdAnswer.text = "3 - ${randomRecipes[2].cuisine}"
            fourthAnswer.text = "4 - ${randomRecipes[3].cuisine}"
        }
    }
    private fun selectAnswer() : RecipeEntity? {

        var chosenAnswer : RecipeEntity? = null
        val yellowColor = ContextCompat.getColor(requireContext(), R.color.yellow_600)

        binding.apply {

            answerOne.setOnClickListener {
                changeColorToYellow(0)
                changeColorToGray(0)
                chosenAnswer = randomRecipes[0]
            }

            answerTwo.setOnClickListener {
                changeColorToYellow(1)
                changeColorToGray(1)
                chosenAnswer = randomRecipes[1]
            }

            answerThree.setOnClickListener {
                changeColorToYellow(2)
                changeColorToGray(2)
                chosenAnswer = randomRecipes[2]
            }

            answerFour.setOnClickListener {
                changeColorToYellow(3)
                changeColorToGray(3)
                chosenAnswer = randomRecipes[3]
            }
        }

        return chosenAnswer
    }
    private fun changeColorToGreen(answerId : Int){

        val greenColor = ContextCompat.getColor(requireContext(), R.color.green_100)
        when(answerId){

            0 -> binding.answerOne.setBackgroundColor(greenColor)
            1 -> binding.answerTwo.setBackgroundColor(greenColor)
            2 -> binding.answerThree.setBackgroundColor(greenColor)
            3 -> binding.answerFour.setBackgroundColor(greenColor)
        }
    }
    private fun changeColorToGray(answerId : Int){

        val grayColor = ContextCompat.getColor(requireContext(), R.color.gray_200)
        when(answerId){

            0 ->{

                binding.answerTwo.setBackgroundColor(grayColor)
                binding.answerThree.setBackgroundColor(grayColor)
                binding.answerFour.setBackgroundColor(grayColor)
            }

            1 ->{

                binding.answerOne.setBackgroundColor(grayColor)
                binding.answerThree.setBackgroundColor(grayColor)
                binding.answerFour.setBackgroundColor(grayColor)
            }

            2 ->{

                binding.answerTwo.setBackgroundColor(grayColor)
                binding.answerOne.setBackgroundColor(grayColor)
                binding.answerFour.setBackgroundColor(grayColor)
            }

            3 ->{

                binding.answerTwo.setBackgroundColor(grayColor)
                binding.answerThree.setBackgroundColor(grayColor)
                binding.answerOne.setBackgroundColor(grayColor)
            }
        }
    }
    private fun changeColorToYellow(answerId : Int){

        val yellowColor = ContextCompat.getColor(requireContext(), R.color.yellow_600)
        when(answerId){

            0 -> binding.answerOne.setBackgroundColor(yellowColor)
            1 -> binding.answerTwo.setBackgroundColor(yellowColor)
            2 -> binding.answerThree.setBackgroundColor(yellowColor)
            3 -> binding.answerFour.setBackgroundColor(yellowColor)
        }
    }
    private fun changeColorToRed(answerId : Int){

        val redColor = ContextCompat.getColor(requireContext(), R.color.red_100)
        when(answerId){

            0 -> binding.answerOne.setBackgroundColor(redColor)
            1 -> binding.answerTwo.setBackgroundColor(redColor)
            2 -> binding.answerThree.setBackgroundColor(redColor)
            3 -> binding.answerFour.setBackgroundColor(redColor)
        }
    }
}