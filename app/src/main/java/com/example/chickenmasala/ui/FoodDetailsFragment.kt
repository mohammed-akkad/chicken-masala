package com.example.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor
import com.example.chickenmasala.data.interactors.GetRecipesOfCuisineInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentFoodDetailsBinding
import com.example.chickenmasala.ui.screen.BaseFragment
import com.example.chickenmasala.util.Constants
import com.example.chickenmasala.util.Constants.RECIPES_CSV_FILE_NAME

class FoodDetailsFragment : BaseFragment<FragmentFoodDetailsBinding>() {
    override val LOG_TAG: String = FoodDetailsFragment::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentFoodDetailsBinding = FragmentFoodDetailsBinding::inflate

    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var lastRelativeRecipeEntity: List<RecipeEntity>

    override fun onStart() {
        super.onStart()
        val recipeEntity =
            arguments?.getParcelable<RecipeEntity>(Constants.TransitionKeys.RECIPE_LIST_KEY)
        recipeEntity?.let { updateFoodDetailsViews(it) }
    }

    override fun setup() {
    }

    override fun addCallBacks() {
        binding.apply {
            cardFirstRelativeFood.setOnClickListener {
                addFragmentWithObject(FoodDetailsFragment(), lastRelativeRecipeEntity[0])
            }
            cardSecondRelativeFood.setOnClickListener {
                addFragmentWithObject(FoodDetailsFragment(), lastRelativeRecipeEntity[1])
            }
        }
    }

    private fun updateFoodDetailsViews(recipeEntity: RecipeEntity) {
        binding.apply {
            tvFoodName.text = recipeEntity.name
            tvFoodDetailName.text = recipeTextStyle(recipeEntity.cleanedIngredients)
            tvFoodDescription.text = recipeTextStyle(recipeEntity.ingredients)
            loadImageFromNetwork(recipeEntity.imageUrl, imageView)
            lastRelativeRecipeEntity = getAllFoodRelativeInformation(recipeEntity.cuisine, 2)
            updateRelativeFoodViews(lastRelativeRecipeEntity)
        }
    }

    private fun getAllFoodRelativeInformation(cuisine: String, limit: Int): List<RecipeEntity> {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        val getRecipesOfCuisineInteractor = GetRecipesOfCuisineInteractor(dataSourceOfRecipeEntity)
        val recipesOfCuisineList = getRecipesOfCuisineInteractor.execute(cuisine, limit)
        return recipesOfCuisineList
    }

    private fun updateRelativeFoodViews(list: List<RecipeEntity>) {
        binding.apply {
            tvFirstRelativeFoodName.text = list[0].name
            tvSecondRelativeFoodName.text = list[1].name
            loadImageFromNetwork(list[0].imageUrl, imgFirstRelativeFood)
        }
        loadImageFromNetwork(list[1].imageUrl, binding.imgSecondRelativeFood)
    }

    private fun getRecipeBasedOnRecipeName(name: String): RecipeEntity {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)
        val recipesBasedOnRecipeNameList =
            GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor(
                dataSourceOfRecipeEntity
            )
        val recipesList =
            recipesBasedOnRecipeNameList.execute(recipeName = name)
        return recipesList[0]
    }

    private fun loadImageFromNetwork(url: String, imageView: ImageView) {
        Glide.with(this@FoodDetailsFragment).load(url)
            .placeholder(R.drawable.ic_test_download)
            .error(R.drawable.ic_test_error)
            .into(imageView)
    }

    private fun recipeTextStyle(list: List<String>): String {
        var result = ""
        var i = 1
        for (item in list) {
            val words = item.split(",")
            for (word in words) {
                result += "$i. $word\n"
                i++
            }
        }
        return result
    }

    private fun addFragmentWithObject(fragment: Fragment, recipeEntity: RecipeEntity) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        recipeEntity.let {
            val bundle = Bundle()
            bundle.putParcelable(Constants.TransitionKeys.RECIPE_LIST_KEY, recipeEntity)
            fragment.arguments = bundle
        }
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}