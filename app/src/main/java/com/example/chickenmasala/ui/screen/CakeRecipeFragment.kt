package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentFoodKitchenCategoryBinding
import com.example.chickenmasala.ui.adapter.CakeAdapter
import com.example.chickenmasala.ui.adapter.SweetAdapter
import com.example.chickenmasala.ui.listener.SpecialTreatsListener
import com.example.chickenmasala.util.Constants

class CakeRecipeFragment : BaseFragment<FragmentFoodKitchenCategoryBinding>(),
    SpecialTreatsListener {
    override val LOG_TAG: String = "SweetRecipeFragment"
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    lateinit var cakeAdapter: CakeAdapter


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodKitchenCategoryBinding =
        FragmentFoodKitchenCategoryBinding::inflate

    override fun setup() {
        setupDateCake()

    }

    override fun addCallBacks() {
        binding.apply {
            itemCard.apply {
                adapter = cakeAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
            }

        }
    }

    private fun setupDateCake() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)
        val list = dataSourceOfRecipeEntity.getAllItems()
            .filter { it.cleanedIngredients.toString().contains("cake ") }.shuffled()
        cakeAdapter = CakeAdapter(list, this)
    }

    private fun navigationBetweenParentFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun onClickItemRecipeEntity(recipeEntity: String) {
        val foodDetailsFragment = FoodDetailsFragment.newInstance(recipeEntity)
        navigationBetweenParentFragment(foodDetailsFragment)

    }
}