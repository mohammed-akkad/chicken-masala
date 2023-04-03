package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentRecyclerBinding
import com.example.chickenmasala.ui.adapter.RecipeCardAdapter
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.util.Constants


class ForYouFragment : BaseFragment<FragmentRecyclerBinding>(), RecipeInteractionListener {
    override val LOG_TAG: String = ForYouFragment::class.java.name
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    lateinit var getAListOfRandomRecipesInteractor: GetAListOfRandomRecipesInteractor
    private lateinit var forYouAdapter: RecipeCardAdapter


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecyclerBinding =
        FragmentRecyclerBinding::inflate

    override fun setup() {
        setupDataRecipesEntity()
    }

    private fun setupDataRecipesEntity() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)
        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)
        val listAllForYou = getAListOfRandomRecipesInteractor.execute(45)
        forYouAdapter = RecipeCardAdapter(listAllForYou, this)
    }

    override fun addCallBacks() {
        binding.itemsRecycler.apply {
            adapter = forYouAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
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