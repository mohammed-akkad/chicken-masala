package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.FoodDataSource
import com.example.chickenmasala.data.interactors.GetAllCuisineImageUrlsAndNamesInteractor
import com.example.chickenmasala.data.interactors.GetRecipesOfCuisineInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmantKitchensBinding
import com.example.chickenmasala.ui.adapter.KitchensAdapter
import com.example.chickenmasala.util.Constants

class KitchenFragment : BaseFragment<FragmantKitchensBinding>() {
    override val LOG_TAG: String = "KitchenFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmantKitchensBinding =
        FragmantKitchensBinding::inflate
    private lateinit var dataSource: FoodDataSource<RecipeEntity>
    override fun setup() {
        dataSource =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, RecipeParser())
        setupKitchenAdapter()
    }

    override fun addCallBacks() {}

    private fun setupKitchenAdapter() {
        val recipesList = GetAllCuisineImageUrlsAndNamesInteractor(dataSource).execute()
        val kitchensAdapter = KitchensAdapter(recipesList, ::onClickKitchenItem)
        binding.recyclerKitchens.adapter = kitchensAdapter
    }

    private fun onClickKitchenItem(cuisine: String) {
        val foodKitchenCategoryFragment = FoodKitchenCategoryFragment()
        val recipesOfKitchen = GetRecipesOfCuisineInteractor(dataSource).execute(cuisine)
        foodKitchenCategoryFragment.arguments = Bundle().apply {
            putParcelableArrayList(Constants.KEY_RECIPES_LIST, ArrayList(recipesOfKitchen))
        }
        addFragment(foodKitchenCategoryFragment)
    }

    private fun addFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}