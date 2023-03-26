package com.example.chickenmasala

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.databinding.FragmentGuessTheIngredientBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class GuessTheIngredientFragment : BaseFragment<FragmentGuessTheIngredientBinding>() {

    private lateinit var dataSource: CsvDataSource<RecipeEntity>
    private lateinit var randomListOfData : GetAListOfRandomRecipesInteractor
    var randomRecipes = randomListOfData.execute(4)
    var randomRecipe = randomRecipes.random()
    override val LOG_TAG: String = "GuessTheIngredientFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheIngredientBinding = FragmentGuessTheIngredientBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {

    }

    private fun prepareQuestion(){

        val questionText = "What is the invalid ingredient in " + "${randomRecipe.name} ? "
        binding.guessTheInvalidIngredientQuestion.text = questionText
    }


}