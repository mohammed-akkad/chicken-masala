package com.example.chickenmasala.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.chickenmasala.R
import com.example.chickenmasala.databinding.FragmentFoodDetailsBinding
import com.example.chickenmasala.util.Constants

class FoodDetailsFragment : BaseFragment<FragmentFoodDetailsBinding>() {


    override val LOG_TAG: String = "FragmentDetails"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
        FragmentFoodDetailsBinding = FragmentFoodDetailsBinding::inflate


    override fun onStart() {
        super.onStart()
        arguments?.let {
            Constants.TransitionKeys.apply {
                updateFoodDetailsImageViews(
                    getSelectedFoodValue(it, SELECTED_FOOD_IMAGE),
                    getSelectedFoodValue(it, SELECTED_FIRST_RELATIVE_FOOD_IMAGE),
                    getSelectedFoodValue(it, SELECTED_SECOND_RELATIVE_FOOD_IMAGE))

                updateFoodDetailsTextViews(
                    getSelectedFoodValue(it, SELECTED_FOOD_NAME),
                    getSelectedFoodValue(it, SELECTED_FOOD_DETAIL_NAME),
                    getSelectedFoodValue(it, SELECTED_FOOD_DESCRIPTION),
                    getSelectedFoodValue(it, SELECTED_FIRST_RELATIVE_FOOD_NAME),
                    getSelectedFoodValue(it, SELECTED_SECOND_RELATIVE_FOOD_NAME))
            }


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


    private fun getSelectedFoodValue(bundle: Bundle, key: String) = bundle.getString(key)

    private fun updateFoodDetailsImageViews(
        foodImageUrl: String?,
        firstRelativeFoodImageUrl:String?,
        secondRelativeFoodImageUrl:String?){
        binding.apply {
            foodImageUrl?.let { loadImageFromNetwork(it, imageView) }
            firstRelativeFoodImageUrl?.let { loadImageFromNetwork(it, imgFirstRelativeFood) }
            secondRelativeFoodImageUrl?.let { loadImageFromNetwork(it, imgSecondRelativeFood) }
        }

    }

    private fun updateFoodDetailsTextViews(
        name:String?, detailName:String?,
        description:String?, firstRelativeFoodName:String?,
        secondRelativeFoodName: String?
    ){
        binding.apply {
            tvFoodName.text = name
            tvFoodDetailName.text = detailName
            tvFoodDescription.text = description
            tvFirstRelativeFoodName.text = firstRelativeFoodName
            tvSecondRelativeFoodName.text = secondRelativeFoodName
        }
    }

    private fun loadImageFromNetwork(url: String, imageView: ImageView){
        Glide.with(this@FoodDetailsFragment).load(url)
            .placeholder(R.drawable.ic_test_download)
            .error(R.drawable.ic_test_error)
            .into(imageView)
    }

}