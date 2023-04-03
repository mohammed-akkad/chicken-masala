package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfSweetRecipesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentRecyclerBinding
import com.example.chickenmasala.ui.adapter.RecipeCardAdapter
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.util.Constants

class SweetRecipeFragment : BaseFragment<FragmentRecyclerBinding>(),
    RecipeInteractionListener {
    override val LOG_TAG: String = SweetRecipeFragment::class.java.name
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    lateinit var sweetAdapter: RecipeCardAdapter
    lateinit var getAListOfSweetRecipesInteractor: GetAListOfSweetRecipesInteractor

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecyclerBinding =
        FragmentRecyclerBinding::inflate

    override fun setup() {
        setDataSweet()
    }

    override fun addCallBacks() {
        binding.apply {
            itemsRecycler.apply {
                adapter = sweetAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)
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
        sweetAdapter = RecipeCardAdapter(list, this)
    }

    override fun onClickItemRecipeEntitty(recipeEntity: RecipeEntity) {
        val fragment = com.example.chickenmasala.ui.FoodDetailsFragment()
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