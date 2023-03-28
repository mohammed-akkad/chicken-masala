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
        val name = arguments?.getString("name")

    }



override fun addCallBacks() {
    binding.apply {

        tvFoodName.text = arguments?.getString("name")
        tvFoodDetailName.text = arguments?.getString("cleanedIngredients")
        tvFoodDescription.text = arguments?.getString("ingredients")
        Glide.with(this.root).load(arguments?.getString("imageUrl")).placeholder(R.drawable.cloud_download).into(imageView)
        cookTimeText.setVisibility(View.GONE)
        cardFirstRelativeFood.setVisibility(View.GONE)
        cardSecondRelativeFood.setVisibility(View.GONE)



    }
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