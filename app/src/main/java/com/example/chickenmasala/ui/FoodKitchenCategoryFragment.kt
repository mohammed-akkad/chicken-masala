package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentFoodKitchenCategoryBinding


class FoodKitchenCategoryFragment : BaseFragment<FragmentFoodKitchenCategoryBinding>() {
    override val LOG_TAG: String = "FoodKitchenCategoryFragment"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodKitchenCategoryBinding =
        FragmentFoodKitchenCategoryBinding::inflate

    override fun setup() {

    }

    override fun addCallBacks() {
    }


}