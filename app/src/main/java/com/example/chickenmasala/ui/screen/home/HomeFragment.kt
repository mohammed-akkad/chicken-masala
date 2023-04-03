package com.example.chickenmasala.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.ui.MainActivity
import com.example.chickenmasala.ui.listener.CategoryInteractionListener
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.ui.screen.base.BaseFragment
import com.example.chickenmasala.ui.screen.home.adapter.CategoryAdapter
import com.example.chickenmasala.ui.screen.home.adapter.SliderAdapter
import com.example.chickenmasala.ui.screen.kitchens.FoodKitchenCategoryFragment
import com.example.chickenmasala.ui.screen.recipe.FoodDetailsFragment
import com.example.chickenmasala.ui.screen.recipe.adapter.RecipeCardAdapter
import com.example.chickenmasala.ui.screen.recipe.RecipesRelatedCategoriesFragment
import com.example.chickenmasala.util.Constants
import com.example.chickenmasala.util.Constants.CATEGORIES_CSV_FILE_NAME
import com.example.chickenmasala.util.Constants.RECIPES_CSV_FILE_NAME

class HomeFragment : BaseFragment<FragmentHomeBinding>(), CategoryInteractionListener,
    RecipeInteractionListener {

    override val LOG_TAG: String = HomeFragment::class.java.name
    override var isShowBackButton: Boolean = false
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var getAListOfRandomRecipesInteractor: GetAListOfRandomRecipesInteractor
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var forYouAdapter: RecipeCardAdapter
    private lateinit var sweetAdapter: RecipeCardAdapter
    private lateinit var cakeAdapter: RecipeCardAdapter
    private val bundle = Bundle()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun setup() {
        setDataSliderAndForYou()
        setupDataCategoryEntity()
        setTitleAppBar(getString(R.string.home))
        setDataSweetTreats()
        setDataCakeTreats()
    }

    private fun setupDataRecipesEntity() {
        val csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)
        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)
    }

    private fun setDataSweetTreats() {
        setupDataRecipesEntity()
        val listOfSweet = dataSourceOfRecipeEntity.getAllItems()
            .filter { it.cleanedIngredients.toString().contains(FILTER_SWEET) }.shuffled()
            .take(LIMIT_LIST_RECIPE)
        sweetAdapter = RecipeCardAdapter(listOfSweet, this)
    }

    private fun setDataSliderAndForYou() {
        setupDataRecipesEntity()
        val listOfSlider = getAListOfRandomRecipesInteractor.execute(LIMIT_LIST_RECIPE)
        val listForYou = getAListOfRandomRecipesInteractor.execute(LIMIT_LIST_RECIPE)
        sliderAdapter = SliderAdapter(listOfSlider)
        forYouAdapter = RecipeCardAdapter(listForYou, this)
        bundle.putParcelableArrayList(Constants.KEY_RECIPES_LIST, ArrayList(listOfSlider))
        val forYouRecipesFragment = ForYouFragment()
        forYouRecipesFragment.arguments = bundle
    }

    private fun setDataCakeTreats() {
        setupDataRecipesEntity()
        val listOfCake = dataSourceOfRecipeEntity.getAllItems()
            .filter { it.cleanedIngredients.toString().contains(FILTER_CAKE) }.shuffled()
            .take(LIMIT_LIST_RECIPE)
        cakeAdapter = RecipeCardAdapter(listOfCake, this)
    }

    private fun setupDataCategoryEntity() {
        val csvCategoryParser = CategoryParser()
        val dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity> =
            CsvDataSource(requireContext(), CATEGORIES_CSV_FILE_NAME, csvCategoryParser)
        val listOfCategory =
            dataSourceOfCategoryEntity.getAllItems().shuffled().take(LIMIT_LIST_RECIPE)
        categoryAdapter = CategoryAdapter(listOfCategory, this)
    }

    override fun addCallBacks() {
        binding.apply {
            sliderRecycler.adapter = sliderAdapter
            categoriesRecycler.adapter = categoryAdapter
            sweetTreatsRecycler.adapter = sweetAdapter
            forYouRecycler.adapter = forYouAdapter
            cakeCornerRecycler.adapter = cakeAdapter
            seeAllCategories.setOnClickListener {
                navigationBetweenFragment(FoodKitchenCategoryFragment())
                setTitleAppBar(getString(R.string.food_categories))
            }
            textviewSeeAllForYou.setOnClickListener {
                navigationBetweenFragment(ForYouFragment())
                setTitleAppBar(getString(R.string.for_you))
            }
            seeAllSweetTreats.setOnClickListener {
                navigationBetweenFragment(SweetRecipeFragment())
                setTitleAppBar(getString(R.string.sweet_treats))
            }
            seeAllCakeCorner.setOnClickListener {
                navigationBetweenFragment(CakeRecipeFragment())
                setTitleAppBar(getString(R.string.cake_corner))
            }
        }
    }

    private fun setTitleAppBar(name: String) {
        (activity as MainActivity).apply {
            title = "$name "
        }
    }

    override fun onClickItemCategory(nameCategory: String) {
        val fragment = RecipesRelatedCategoriesFragment.newInstance(nameCategory)
        navigationBetweenFragment(fragment)
    }

    override fun onClickItemRecipeEntitty(recipeEntity: RecipeEntity) {
        val fragment = FoodDetailsFragment()
        bundle.putParcelable(Constants.TransitionKeys.RECIPE_LIST_KEY, recipeEntity)
        fragment.arguments = bundle
        navigationBetweenFragment(fragment)
    }

    private fun navigationBetweenFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        const val LIMIT_LIST_RECIPE = 5
        const val FILTER_CAKE = "cake "
        const val FILTER_SWEET = "sugar "
    }
}