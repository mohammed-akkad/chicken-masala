package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentFoodDetailsBinding

class FoodDetailsFragment : BaseFragment<FragmentFoodDetailsBinding>() {


    override val LOG_TAG: String = "FragmentDetails"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailsBinding =
        FragmentFoodDetailsBinding::inflate


    override fun setup() {
    }

    override fun addCallBacks() {
    }

}