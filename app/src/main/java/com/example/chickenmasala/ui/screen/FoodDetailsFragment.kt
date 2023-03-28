package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.data.interactors.GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor
import com.example.chickenmasala.data.interactors.GetAListOfRecipesOfACertainCategoryInteractor
import com.example.chickenmasala.databinding.FragmentFoodDetailsBinding

class FoodDetailsFragment : BaseFragment<FragmentFoodDetailsBinding>() {


    override val LOG_TAG: String = "FragmentDetails"
    lateinit var getAListOfRecipesOfACertainCategoryInteractor: GetAListOfRecipesOfACertainCategoryInteractor
    lateinit var getAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor: GetAListOfRecipesBasedOnRecipeNameWithFiltersAsArgumentsInteractor

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailsBinding =
        FragmentFoodDetailsBinding::inflate


    override fun setup() {
    }

    override fun addCallBacks() {
        binding.apply {

            tvFoodName.text = arguments?.getString("name")
            tvFoodDetailName.text = arguments?.getString("cleanedIngredients")
            tvFoodDescription.text = arguments?.getString("ingredients")
            Glide.with(this.root).load(arguments?.getString("imageUrl")).placeholder(R.drawable.cloud_download).into(imageView)
            textView3.setVisibility(View.GONE)
            cardFirstRelativeFood.setVisibility(View.GONE)
            cardSecondRelativeFood.setVisibility(View.GONE)



        }
    }


}