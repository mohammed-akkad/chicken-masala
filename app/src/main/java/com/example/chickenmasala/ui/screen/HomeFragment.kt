package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.HomeFragmentBinding
import com.example.chickenmasala.ui.listener.CategoryInteractionListener
import com.example.chickenmasala.ui.adapter.CategotyAdapter
import com.example.chickenmasala.ui.adapter.ForYouRecipesAdapter
import com.example.chickenmasala.ui.adapter.RecipesAdapter
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.util.Constants.CATEGORIES_CSV_FILE_NAME
import com.example.chickenmasala.util.Constants.RECIPES_CSV_FILE_NAME

@Suppress("DEPRECATION")
class HomeFragment : BaseFragment<HomeFragmentBinding>(), CategoryInteractionListener  , RecipeInteractionListener{
    override val LOG_TAG: String = "HomeFragment"
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var csvCategoryParser: CategoryParser
    private val foodDetailsFragment = FoodDetailsFragment()
    val bundle = Bundle()
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity>
    private val foodKitchenCategoryFragment = FoodKitchenCategoryFragment()
    private val forYouRecipesFragment = ForYouFragment()
    lateinit var getAListOfRandomRecipesInteractor: GetAListOfRandomRecipesInteractor




    lateinit var recipesAdapter: RecipesAdapter
    lateinit var categotyAdapter: CategotyAdapter
    lateinit var forYouAdapter : ForYouRecipesAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> HomeFragmentBinding =
        HomeFragmentBinding::inflate


    override fun setup() {
        setupDataRecipesEntity()
        setupDataCategoryEntity()
        setNavigationTitleAppBar(getString(R.string.home))

    }


    private fun setupDataRecipesEntity() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)


        val list = getAListOfRandomRecipesInteractor.execute(5)
        recipesAdapter = RecipesAdapter(list)
        forYouAdapter = ForYouRecipesAdapter(list , this)
        bundle.putParcelableArrayList("key" , ArrayList(list))
        forYouRecipesFragment.arguments = bundle

    }

    private fun setupDataCategoryEntity() {
        csvCategoryParser = CategoryParser()
        dataSourceOfCategoryEntity =
            CsvDataSource(requireContext(), CATEGORIES_CSV_FILE_NAME, csvCategoryParser)
        val list = dataSourceOfCategoryEntity.getAllItems().shuffled().take(5)
        categotyAdapter = CategotyAdapter(list, this)
    }

    override fun addCallBacks() {

        binding.apply {
            recyclerLargeHome.adapter = recipesAdapter
            recyclerCategoryHome.adapter = categotyAdapter
            recyclerForYouHome.adapter = forYouAdapter

            textSeeAllCategories.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar(getString(R.string.food_categories))
            }
            textSeeAllForYou.setOnClickListener {
                navigationBetweenFragment(forYouRecipesFragment)
                setNavigationTitleAppBar(getString(R.string.for_you))
            }
            textSeeAllSweetTreats.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar(getString(R.string.sweet_treats))
            }
            textSeeAllCakeCorner.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar(getString(R.string.cake_corner))
            }

        }

    }

    private fun navigationBetweenFragment(fragment: Fragment) {

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }


    }
    private fun navigationBetweenParentFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()

    }

    private fun setNavigationTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name "
        }

    }



    override fun onClickItemCategory(nameCategory: String) {
        val fragment = RecipesRelatedCategoriesFragment.newInstance(nameCategory)
        navigationBetweenParentFragment(fragment)

    }

    override fun onClickItemRecipeEntitty(recipeEntity: RecipeEntity) {
        navigationBetweenFragment(foodDetailsFragment)
        bundle.putString("name" , recipeEntity.name)
        bundle.putString("cleanedIngredients" , recipeEntity.cleanedIngredients.joinToString())
        bundle.putString("imageUrl" , recipeEntity.imageUrl)
        bundle.putString("ingredients" , recipeEntity.ingredients.joinToString { it })
        foodDetailsFragment.arguments = bundle


    }

}






