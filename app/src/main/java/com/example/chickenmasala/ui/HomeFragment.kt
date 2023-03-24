package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.interactors.GetListRecipesRelatedToCertainRecipeInteractor
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.HomeFragmentBinding
import com.example.chickenmasala.util.Constants.CATEGORIES_CSV_FILE_NAME
import com.example.chickenmasala.util.Constants.RECIPES_CSV_FILE_NAME

class HomeFragment : BaseFragment<HomeFragmentBinding>() {
    override val LOG_TAG: String = "HomeFragment"
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var csvCategoryParser: CategoryParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity>
    private val foodKitchenCategoryFragment = FoodKitchenCategoryFragment()
    lateinit var getAListOfRandomRecipesInteractor: GetAListOfRandomRecipesInteractor

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
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)


        val list = getAListOfRandomRecipesInteractor.execute(5)
        recipesAdapter = RecipesAdapter(list)

    }

    private fun setupDataCategoryEntity() {
        csvCategoryParser = CategoryParser()
        dataSourceOfCategoryEntity =
            CsvDataSource(requireContext(), CATEGORIES_CSV_FILE_NAME, csvCategoryParser)
        val list = dataSourceOfCategoryEntity.getAllItems().shuffled().take(5)
        categotyAdapter = CategotyAdapter(list)

    }

    override fun addCallBacks() {

        binding.apply {
            recyclerLargeHome.adapter = recipesAdapter
            recyclerCategoryHome.adapter = categotyAdapter

            textSeeAllCategories.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar(getString(R.string.food_categories))
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






