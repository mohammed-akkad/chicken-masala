package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAllCuisineImageUrlsAndNamesInteractor
import com.example.chickenmasala.data.interactors.GetListRecipesRelatedToCertainRecipeInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentFoodKitchenCategoryBinding
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.ui.adapter.CategorySpacificAdapter
import com.example.chickenmasala.util.Constants

class RecipesRelatedCategoriesFragment : BaseFragment<FragmentFoodKitchenCategoryBinding>(),
    RecipeInteractionListener {
    override val LOG_TAG: String = "RecipesRelatedCategoriesFragment"
    private lateinit var csvRecipeParser: RecipeParser
    var dataCategories: String? = null
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    lateinit var getListRecipesRelatedToCertainRecipeInteractor: GetListRecipesRelatedToCertainRecipeInteractor
    lateinit var categorySpacificAdapter: CategorySpacificAdapter
    lateinit var getAllCuisineImageUrlsAndNamesInteractor: GetAllCuisineImageUrlsAndNamesInteractor
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
    }

    override fun addCallBacks() {
        binding.apply {
            itemCard.apply {
                adapter = categorySpacificAdapter

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

        getAllCuisineImageUrlsAndNamesInteractor = GetAllCuisineImageUrlsAndNamesInteractor(dataSourceOfRecipeEntity)

        val list = getListRecipesRelatedToCertainRecipeInteractor.executeAllRecipe(
            limit = 20,
            )

        val newList  = dataSourceOfRecipeEntity.getAllItems().distinct().shuffled().distinctBy {
            it.cuisine
        }.map {
            it.cuisine
        }
        Log.d(LOG_TAG, "$newList ")
        categorySpacificAdapter = CategorySpacificAdapter(list,this)


    }

    private fun setNavigationTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name Food "
        }
    }

    private fun navigationBetweenParentFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }


    override fun onClickItemRecipeEntitty(recipeEntity: RecipeEntity) {
        val fragment = FoodDetailsFragment()
        navigationBetweenParentFragment(fragment)

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