package com.example.chickenmasala.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.interactors.GetAListOfSweetRecipesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentRecyclerBinding
import com.example.chickenmasala.ui.screen.recipe.adapter.RecipeHorizontalAdapter
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.ui.screen.recipe.FoodDetailsFragment
import com.example.chickenmasala.ui.screen.base.BaseFragment
import com.example.chickenmasala.util.Constants

class SweetRecipeFragment : BaseFragment<FragmentRecyclerBinding>(),
    RecipeInteractionListener {
    override val LOG_TAG: String = SweetRecipeFragment::class.java.name
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    lateinit var sweetAdapter: RecipeHorizontalAdapter
    lateinit var getAListOfSweetRecipesInteractor: GetAListOfSweetRecipesInteractor
    override var isShowBackButton: Boolean = true

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecyclerBinding =
        FragmentRecyclerBinding::inflate

    override fun setup() {
        setDataSweet()
    }

    override fun addCallBacks() {
        binding.apply {
            itemsRecycler.apply {
                adapter = sweetAdapter
            }
        }
    }

    private fun setupDateSweet() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)
        getAListOfSweetRecipesInteractor = GetAListOfSweetRecipesInteractor(dataSourceOfRecipeEntity)

    }

    private fun setDataSweet(){
        setupDateSweet()
        val list = getAListOfSweetRecipesInteractor.execute(45)
        sweetAdapter = RecipeHorizontalAdapter(list, this)
    }

    override fun onClickItemRecipeEntitty(recipeEntity: RecipeEntity) {
        val fragment = FoodDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable(Constants.TransitionKeys.RECIPE_LIST_KEY, recipeEntity)
        fragment.arguments = bundle
        navigationBetweenParentFragment(fragment)

    }
    private fun navigationBetweenParentFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}