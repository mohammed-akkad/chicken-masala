package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.R
import com.example.chickenmasala.data.interactors.GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor
import com.example.chickenmasala.data.interactors.GetAListOfRecipesOfACertainCategoryInteractor
import com.example.chickenmasala.databinding.FragmentFoodDetailsBinding
import com.example.chickenmasala.util.Constants

class FoodDetailsFragment : BaseFragment<FragmentFoodDetailsBinding>() {


    override val LOG_TAG: String = "FragmentDetails"
    lateinit var getAListOfRecipesOfACertainCategoryInteractor: GetAListOfRecipesOfACertainCategoryInteractor
    lateinit var getAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor: GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor
    var dataCakeRecipes: String? = null
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailsBinding =
        FragmentFoodDetailsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataCakeRecipes = it.getString(Constants.KEY_DATA_SET)
        }

    }


    override fun setup() {

    }


    override fun addCallBacks() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)
        val list =
            dataSourceOfRecipeEntity.getAllItems().filter { it.name == dataCakeRecipes }.shuffled()
        val listRelatedSweet = dataSourceOfRecipeEntity.getAllItems()
            .filter { it.cleanedIngredients.toString().contains("sugar ") }.shuffled().take(2)

        val imageSweet = list.map { it.imageUrl }[0]
        val nameRecipe = list.joinToString { it.name }
        val ingredient = list.map { it.ingredients }.joinToString()

        val cleanIngredient = list.map { it.cleanedIngredients }.joinToString()

        val imageSweetOneRelated = listRelatedSweet.map { it.imageUrl }[0]
        val imageSweetTwoRelated = listRelatedSweet.map { it.imageUrl }[1]
        val nameSweetOneRelated = listRelatedSweet.map { it.name }[0]
        val nameSweetTwoRelated = listRelatedSweet.map { it.name }[1]

        binding.apply {
            spicficGlide(binding, imageSweet, imageView)
            tvFoodName.text = nameRecipe
            tvFoodDescription.text = cleanIngredient
            tvFirstRelativeFoodName.text = nameSweetOneRelated
            tvSecondRelativeFoodName.text = nameSweetTwoRelated

            tvFoodDetailName.text = ingredient
            spicficGlide(binding, imageSweetOneRelated, imgFirstRelativeFood)
            spicficGlide(binding, imageSweetTwoRelated, imgSecondRelativeFood)
        }
        dataCakeRecipes?.let { setNavigationTitleAppBar(it) }
    }

    private fun setNavigationTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name Food "
        }


    }

    private fun spicficGlide(
        binding: FragmentFoodDetailsBinding,
        imageUrl: String,
        imageView: ImageView
    ) {
        Glide
            .with(binding.root.context)
            .load(imageUrl)
            .into(imageView)

    }


    companion object {
        fun newInstance(name: String) =
            FoodDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.KEY_DATA_SET, name)
                }
            }
    }


}