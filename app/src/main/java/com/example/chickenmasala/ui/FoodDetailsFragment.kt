package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetRecipesOfCuisineInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentFoodDetailsBinding
import com.example.chickenmasala.ui.screen.BaseFragment
import com.example.chickenmasala.util.Constants
import com.example.chickenmasala.util.Constants.RECIPES_CSV_FILE_NAME
import java.util.ArrayList

class FoodDetailsFragment : BaseFragment<FragmentFoodDetailsBinding>() {

    override val LOG_TAG: String = "FragmentDetails"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
        FragmentFoodDetailsBinding = FragmentFoodDetailsBinding::inflate

    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var csvRecipeParser: RecipeParser
    lateinit var name:String
    lateinit var ingredients:List<String>
    lateinit var imageUrl:String
    lateinit var cleanedIngredients:List<String>
    lateinit var cuisine:String

    override fun onStart() {
        super.onStart()


            val foodDetailsList = arguments?.
            getParcelableArrayList<RecipeEntity>(Constants.TransitionKeys.RECIPE_LIST_KEY)

            getAllFoodDetailsInformation(foodDetailsList)
            foodDetailsList?.let { updateFoodDetailsViews(it) }

            val relativeFoodList =
                foodDetailsList?.get(0)?.cuisine?.let { getAllFoodRelativeInformation(it,2) }
            relativeFoodList?.let { updateRelativeFoodViews(it) }



    }

    private fun updateFoodDetailsViews(list:List<RecipeEntity>) {
        binding.apply {
            tvFoodName.text = list[0].name
            tvFoodDetailName.text = list[0].cleanedIngredients.toString()
            tvFoodDescription.text = list[0].ingredients.toString()

             loadImageFromNetwork(list[0].url, imageView)
        }
    }

    override fun setup() {
    }
    override fun addCallBacks() {
        binding.apply {
            cardFirstRelativeFood.setOnClickListener {

            }

            cardSecondRelativeFood.setOnClickListener {

            }
        }
    }
    private fun getAllFoodDetailsInformation(foodDetailsList: ArrayList<RecipeEntity>?) {
        foodDetailsList?.forEach { recipe ->
            name = recipe.name
            ingredients = recipe.ingredients
            imageUrl = recipe.imageUrl
            cleanedIngredients = recipe.cleanedIngredients
            cuisine = recipe.cuisine
        }
    }



    private fun getAllFoodRelativeInformation(cuisine: String, limit:Int): List<RecipeEntity> {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        val getRecipesOfCuisineInteractor = GetRecipesOfCuisineInteractor(dataSourceOfRecipeEntity)
        val recipesOfCuisineList = getRecipesOfCuisineInteractor.execute(cuisine, limit)
        return recipesOfCuisineList
    }

    private fun updateRelativeFoodViews(list:List<RecipeEntity>){
        binding.apply {
            tvFirstRelativeFoodName.text = list[0].name
            tvSecondRelativeFoodName.text = list[1].name

            loadImageFromNetwork(list[0].imageUrl, imgFirstRelativeFood) }
        loadImageFromNetwork(list[1].imageUrl, binding.imgSecondRelativeFood) }



    private fun loadImageFromNetwork(url: String, imageView: ImageView){
        Glide.with(this@FoodDetailsFragment).load(url)
            .placeholder(R.drawable.ic_test_download)
            .error(R.drawable.ic_test_error)
            .into(imageView)
    }

    }














