package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentFoodDetailsBinding
import com.example.chickenmasala.util.Constants

class FoodDetailsFragment : BaseFragment<FragmentFoodDetailsBinding>() {


    override val LOG_TAG: String = "FragmentDetails"
    var dataCakeRecipes: String? = null


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailsBinding =
        FragmentFoodDetailsBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataCakeRecipes = it.getString(Constants.KEY_DATA_SET)
        }

    }



    override fun setup() {
        log("$dataCakeRecipes")
    }

    override fun addCallBacks() {
        binding.apply {


        }
        dataCakeRecipes?.let { setNavigationTitleAppBar(it) }
    }
    private fun setNavigationTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name Food "
        }

    }




    companion object {
        fun newInstance(name: String) =
            RecipesRelatedCategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.KEY_DATA_SET, name)
                }
            }
    }


}