package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.FoodDataSource
import com.example.chickenmasala.data.interactors.GetAllCuisineImageUrlsAndNamesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentRecyclerBinding
import com.example.chickenmasala.ui.adapter.LargeCardAdapter
import com.example.chickenmasala.util.Constants

class KitchenFragment : BaseFragment<FragmentRecyclerBinding>() {
    override val LOG_TAG: String = "KitchenFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecyclerBinding =
        FragmentRecyclerBinding::inflate
    private lateinit var dataSource: FoodDataSource<RecipeEntity>
    override fun setup() {
        dataSource =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, RecipeParser())
        setupKitchenAdapter()
    }

    override fun addCallBacks() {}

    private fun setupKitchenAdapter() {
        val recipesList = GetAllCuisineImageUrlsAndNamesInteractor(dataSource).execute().map {
            Pair(it.cuisine,it.imageUrl)
        }
        val kitchensAdapter = LargeCardAdapter(recipesList){position: Int ->
            //TODO: Handle on category click listener
        }
        binding.itemsRecycler.adapter = kitchensAdapter
    }

/*    private fun onClickKitchenItem(cuisine: String) {
        val recipesRelatedCategoriesFragment = RecipesRelatedCategoriesFragment()
        val recipesOfKitchen = GetRecipesOfCuisineInteractor(dataSource).execute(cuisine)
        recipesRelatedCategoriesFragment.arguments = Bundle().apply {
            putParcelableArrayList(Constants.KEY_RECIPES_LIST, ArrayList(recipesOfKitchen))
        }
        addFragment(recipesRelatedCategoriesFragment)
    }*/

/*    private fun addFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }*/
}