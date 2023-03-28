package com.example.chickenmasala.ui

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.databinding.FragmentFoodKitchenCategoryBinding
import com.example.chickenmasala.util.Constants


class FoodKitchenCategoryFragment : BaseFragment<FragmentFoodKitchenCategoryBinding>() {
    override val LOG_TAG: String = "FoodKitchenCategoryFragment"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodKitchenCategoryBinding =
        FragmentFoodKitchenCategoryBinding::inflate

    override fun setup() {
        arguments?.let {
            val list = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelableArrayList(Constants.KEY_RECIPES_LIST,RecipeEntity::class.java)?.toList()
            } else {
                it.getParcelableArrayList<RecipeEntity>(Constants.KEY_RECIPES_LIST)?.toList()
            }
            list?.forEach {recipe->
                Log.e(LOG_TAG, "item: $recipe", )
            }
        }
    }

    override fun addCallBacks() {
    }


}