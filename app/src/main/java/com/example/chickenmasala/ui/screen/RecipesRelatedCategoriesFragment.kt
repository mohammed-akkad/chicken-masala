package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentRecyclerBinding
import com.example.chickenmasala.ui.adapter.RecipeHorizontalAdapter
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.util.Constants

class RecipesRelatedCategoriesFragment : BaseFragment<FragmentRecyclerBinding>(),
    RecipeInteractionListener {

    override val LOG_TAG: String = RecipesRelatedCategoriesFragment::class.java.name
    var dataCategories: String? = null
    lateinit var recipeHorizontalAdapter: RecipeHorizontalAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecyclerBinding =
        FragmentRecyclerBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataCategories = it.getString(Constants.KEY_DATA_SET)
        }
    }

    override fun setup() {
        setupDataCategorySpecific()
    }

    override fun addCallBacks() {
        binding.apply {
            itemsRecycler.apply {
                adapter = recipeHorizontalAdapter
            }
        }
        dataCategories?.let { setNavigationTitleAppBar(it) }
    }

    private fun setupDataCategorySpecific() {
        val csvRecipeParser = RecipeParser()
        val dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)

        val list = dataSourceOfRecipeEntity.getAllItems()
            .shuffled()
        recipeHorizontalAdapter = RecipeHorizontalAdapter(list, this)
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
        val fragment = com.example.chickenmasala.ui.FoodDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable(Constants.TransitionKeys.RECIPE_LIST_KEY, recipeEntity)
        fragment.arguments = bundle
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