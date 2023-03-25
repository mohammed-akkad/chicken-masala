package com.example.chickenmasala.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.interactors.GetListRecipesRelatedToCertainRecipeInteractor
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentFoodKitchenCategoryBinding
import com.example.chickenmasala.util.Constants


class FoodKitchenCategoryFragment : BaseFragment<FragmentFoodKitchenCategoryBinding>() {
    override val LOG_TAG: String = "FoodKitchenCategoryFragment"
    private lateinit var csvCategoryParser: CategoryParser
    private lateinit var csvRecipeParser: RecipeParser
    var dataCategories: String? = null
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity>
    lateinit var getListRecipesRelatedToCertainRecipeInteractor: GetListRecipesRelatedToCertainRecipeInteractor
    lateinit var categorySpacificAdapter: CategorySpacificAdapter
    lateinit var allCategoryAdapter: AllCategoryAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodKitchenCategoryBinding =
        FragmentFoodKitchenCategoryBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataCategories = it.getString(Constants.KEY_DATA_SET)
        }

    }

    override fun setup() {
        setupDataCategorySpecfic()
        setupDateAllCAtegory()

    }

    private fun setupDateAllCAtegory() {
        csvCategoryParser = CategoryParser()
        dataSourceOfCategoryEntity =
            CsvDataSource(requireContext(), Constants.CATEGORIES_CSV_FILE_NAME, csvCategoryParser)


        val list = dataSourceOfCategoryEntity.getAllItems().shuffled()
        Log.d(LOG_TAG, "$list")
        allCategoryAdapter = AllCategoryAdapter(list)

    }

    override fun addCallBacks() {
        binding.apply {
            itemCard.apply {
                adapter = categorySpacificAdapter
                adapter = allCategoryAdapter
            }
        }
        dataCategories?.let { setNavigationTitleAppBar(it) }
    }


    private fun setupDataCategorySpecfic() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getListRecipesRelatedToCertainRecipeInteractor =
            GetListRecipesRelatedToCertainRecipeInteractor(dataSourceOfRecipeEntity)

        val list = getListRecipesRelatedToCertainRecipeInteractor.executeRecipe(
            limit = 20,
            categories = "$dataCategories"
        )
        Log.d(LOG_TAG, "$list")
        categorySpacificAdapter = CategorySpacificAdapter(list)


    }

    private fun setNavigationTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name Food "
        }

    }


    companion object {
        fun newInstance(name: String) =
            FoodKitchenCategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.KEY_DATA_SET, name)
                }
            }
    }


}