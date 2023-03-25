package com.example.chickenmasala

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.databinding.FragmentGuessTheCuisineBinding


class GuessTheCuisineFragment : BaseFragment<FragmentGuessTheCuisineBinding>() {
    lateinit var dataSource: CsvDataSource<RecipeEntity>
    lateinit var randomListOfData : GetAListOfRandomRecipesInteractor
    override val LOG_TAG: String = "GuessTheCuisineFrag"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheCuisineBinding =
        FragmentGuessTheCuisineBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {
    }

    private fun makeQuestion(){

        randomListOfData = GetAListOfRandomRecipesInteractor(dataSource)
        val randomRecipe = randomListOfData.execute(1)[0]
        val questionText = "To which cuisine , " + "${randomRecipe.name} " + "belong ? "
        binding.guessTheCuisineQuestion.text = questionText
    }

}