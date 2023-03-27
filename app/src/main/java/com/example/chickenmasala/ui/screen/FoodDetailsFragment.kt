package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.chickenmasala.databinding.FragmentFoodDetailsBinding

class FoodDetailsFragment : BaseFragment<FragmentFoodDetailsBinding>() {


    override val LOG_TAG: String = "FragmentDetails"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailsBinding =
        FragmentFoodDetailsBinding::inflate


    override fun setup() {
        val name = arguments?.getString("name")
    }

    override fun addCallBacks() {
        binding.apply {
            val name = arguments?.getString("name")
            tvFoodDetailName.text = arguments?.getString("name")
            tvFoodDescription.text = arguments?.getString("count")

            Glide.with(this.root).load(arguments?.getString("imageUrl")).into(imageView)
        }
    }

}