package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetListRecipesRelatedToCertainRecipeInteractor
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment<HomeFragmentBinding>() {
    override val LOG_TAG: String = "HomeFragment"
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var csvCategoryParser: CategoryParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity>
    private val foodKitchenCategoryFragment = FoodKitchenCategoryFragment()
    lateinit var getListRecipesRelatedToCertainRecipeInteractor: GetListRecipesRelatedToCertainRecipeInteractor

    lateinit var recipesAdapter: RecipesAdapter
    lateinit var categotyAdapter: CategotyAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> HomeFragmentBinding =
        HomeFragmentBinding::inflate


    override fun setup() {
        setupDataRecipesEntity()
        setupDataCategoryEntity()

    }

    private fun setupDataRecipesEntity() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), "indian_food.csv", csvRecipeParser)

        getListRecipesRelatedToCertainRecipeInteractor =
            GetListRecipesRelatedToCertainRecipeInteractor(dataSourceOfRecipeEntity)


        val list = dataSourceOfRecipeEntity.getAllItems().take(5)
        recipesAdapter = RecipesAdapter(list)

    }

    private fun setupDataCategoryEntity() {
        csvCategoryParser = CategoryParser()
        dataSourceOfCategoryEntity =
            CsvDataSource(requireContext(), "categories.csv", csvCategoryParser)
        val list = dataSourceOfCategoryEntity.getAllItems().take(5)
        categotyAdapter = CategotyAdapter(list)

    }

    override fun addCallBacks() {

        binding.apply {
            recyclerLargeHome.adapter = recipesAdapter
            recyclerCategoryHome.adapter = categotyAdapter

            textSeeAllCategories.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar("Food Categories")
            }
            textSeeAllSweetTreats.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar("Sweet Treats")
            }

        }

    }

    private fun navigationBetweenFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.container, fragment)
            commit()
        }

    }

    private fun setNavigationTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name "
        }

    }


}






