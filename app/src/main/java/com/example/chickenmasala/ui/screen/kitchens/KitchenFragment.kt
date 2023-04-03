package com.example.chickenmasala.ui.screen.kitchens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.interactors.FoodDataSource
import com.example.chickenmasala.interactors.GetAllCuisineImageUrlsAndNamesInteractor
import com.example.chickenmasala.interactors.GetRecipesOfCuisineInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentRecyclerBinding
import com.example.chickenmasala.ui.MainActivity
import com.example.chickenmasala.ui.listener.CategoryInteractionListener
import com.example.chickenmasala.ui.screen.recipe.RecipesRelatedCategoriesFragment
import com.example.chickenmasala.ui.screen.base.BaseFragment
import com.example.chickenmasala.ui.screen.kitchens.adapter.LargeCardAdapter
import com.example.chickenmasala.util.Constants


class KitchenFragment : BaseFragment<FragmentRecyclerBinding>(),
    CategoryInteractionListener {
    override val LOG_TAG: String = KitchenFragment::class.java.name
    override var isShowBackButton: Boolean = false
    lateinit var allRecipeAdapter: LargeCardAdapter
    private lateinit var dataSource: FoodDataSource<RecipeEntity>


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecyclerBinding =
        FragmentRecyclerBinding::inflate

    override fun setup() {
        dataSource =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, RecipeParser())
        setupDateAllKitchen()
    }

    private fun setupDateAllKitchen() {
        val list = GetAllCuisineImageUrlsAndNamesInteractor(dataSource).execute().map {
            Pair(it.cuisine, it.imageUrl)
        }
        allRecipeAdapter = LargeCardAdapter(list, this)
    }

    override fun addCallBacks() {
        binding.apply {
            itemsRecycler.apply {
                adapter = allRecipeAdapter
            }
        }
    }

    override fun onClickItemCategory(nameCategory: String) {

        val recipesRelatedCategoriesFragment = RecipesRelatedCategoriesFragment()
        val recipesOfKitchen = GetRecipesOfCuisineInteractor(dataSource).execute("indian")
        recipesRelatedCategoriesFragment.arguments = Bundle().apply {
            putParcelableArrayList(Constants.KEY_RECIPES_LIST, ArrayList(recipesOfKitchen))
        }
        addFragment(recipesRelatedCategoriesFragment)
        setTitleAppBar(nameCategory)
    }

    private fun addFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name "
        }
    }

}