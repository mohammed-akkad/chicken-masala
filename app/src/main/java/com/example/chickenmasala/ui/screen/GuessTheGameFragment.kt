package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.View
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
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import kotlinx.coroutines.*

class GuessTheGameFragment(private val gameName: GuessGamesName) :
    BaseFragment<FragmentGuessTheMealBinding>() {
    override val LOG_TAG: String = "GuessTheMealFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheMealBinding =
        FragmentGuessTheMealBinding::inflate
    private lateinit var guessTheMeal: QuestionGames
    private var selectedChoiceId: Int = 0
    private var yellowColor: Int? = null
    private var grayColor: Int? = null
    private var greenColor: Int? = null
    private var redColor: Int? = null
    private var multiChoices: List<TextView>? = null
    override fun setup() {
        val recipeParser = RecipeParser()
        val dataSource =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, recipeParser)
        guessTheMeal = GuessGamesInteractor(dataSource).guessGames(gameName)
        multiChoices =
            listOf(
                binding.answerOneText,
                binding.answerTwoText,
                binding.answerThreeText,
                binding.answerFourText
            )
        showGame()
    }

    private fun showGame() {
        prepareColors()
        prepareMultiChoices()
        prepareQuestion()
        isSubmitButtonEnabled(false)
        prepareNextQuestion(clickChoicesState = true)
    }

    override fun addCallBacks() {
        setColorOfSelectedChoice()
        submitAnswer()
    }

    private fun prepareColors() {
        yellowColor = ContextCompat.getColor(requireContext(), R.color.yellow_600)
        grayColor = ContextCompat.getColor(requireContext(), R.color.gray_200)
        greenColor = ContextCompat.getColor(requireContext(), R.color.green_100)
        redColor = ContextCompat.getColor(requireContext(), R.color.red_100)
        setUpChoicesColor()
    }

    private fun setUpChoicesColor() {
        multiChoices?.forEach { choice ->
            colorizeTheView(choice, grayColor!!)
        }
    }

    private fun prepareMultiChoices() {
        val listOfChoices = getRandomArrangeOfChoices()
        multiChoices?.forEachIndexed { index, textView ->
            textView.text = listOfChoices[index]
        }
    }

    private fun getRandomArrangeOfChoices(): List<String> {
        return listOf(
            guessTheMeal.correctAnswer,
            guessTheMeal.wrongAnswerOne,
            guessTheMeal.wrongAnswerTwo,
            guessTheMeal.wrongAnswerThree
        ).shuffled()
    }

    private fun prepareQuestion() {
        when (gameName) {
            GuessGamesName.GUESS_THE_MEAL -> setImageQuestion()
            GuessGamesName.GUESS_THE_CUISINE -> setTextQuestion(getString(R.string.questionGuessTheCusine))
            GuessGamesName.GUESS_THE_EXSTING_INGREDIENT -> setTextQuestion(getString(R.string.questionGuessTheMeal))
        }
    }

    private fun setTextQuestion(string: String) {
        binding.apply {
            guessTheMealQuestion.text = string.plus(
                guessTheMeal.question
            )
            mealImageView.visibility = View.GONE
        }
    }

    private fun setImageQuestion() {
        binding.apply {
            Glide.with(mealImageView)
                .load(guessTheMeal.question)
                .placeholder(shimmerPlaceholder())
                .into(mealImageView)
            mealImageView.visibility = View.VISIBLE
        }
    }

    private fun shimmerPlaceholder(): ShimmerDrawable {
        val shimmer =
            Shimmer.AlphaHighlightBuilder()
                .setDuration(1800)
                .setBaseAlpha(0.5f)
                .setHighlightAlpha(0.7f)
                .setTilt(45f)
                .setAutoStart(true)
                .build()
        return ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
    }

    private fun setColorOfSelectedChoice() {
        for (selectedChoice in multiChoices!!) {
            selectedChoice.setOnClickListener {
                resetOtherOptionsColors()
                isSubmitButtonEnabled(true)
                colorizeTheView(selectedChoice, yellowColor!!)
                selectedChoiceId = selectedChoice.id
            }
        }
    }

    private fun resetOtherOptionsColors() {
        multiChoices?.forEach {
            colorizeTheView(it, grayColor!!)
        }
    }

    private fun isSubmitButtonEnabled(status: Boolean) {
        binding.submitButton.isEnabled = status
    }

    private fun submitAnswer() {
        binding.submitButton.setOnClickListener {
            val selectedChoice = determineSelectedView()
            if (isCorrectAnswer(selectedChoice)) {
                colorizeTheView(selectedChoice, greenColor!!)
            } else {
                val correctChoice = multiChoices?.find(::isCorrectAnswer)
                colorizeTheView(correctChoice!!, greenColor!!)
                colorizeTheView(selectedChoice, redColor!!)
            }
            prepareNextQuestion(clickChoicesState = false)
        }
    }

    private fun isCorrectAnswer(textView: TextView): Boolean {
        return textView.text.toString() == guessTheMeal.correctAnswer
    }

    private fun determineSelectedView(): TextView {
        return binding.QuestionParentLayout.findViewById(selectedChoiceId)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun prepareNextQuestion(clickChoicesState: Boolean) {
        multiChoices!!.forEach { choice ->
            choice.isClickable = clickChoicesState
        }
        if (!clickChoicesState) {
            GlobalScope.launch {
                requireActivity().runOnUiThread { setup() }
                delay(100)
            }
        }
    }

    private fun colorizeTheView(selectedChoice: TextView, color: Int) {
        selectedChoice.setBackgroundColor(color)
    }

}