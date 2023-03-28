package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.example.chickenmasala.ui.adapter.RecipesAdapter
import com.example.chickenmasala.ui.adapter.SweetAdapter
import com.example.chickenmasala.ui.listener.SweetTreatsListener
import com.example.chickenmasala.util.Constants.CATEGORIES_CSV_FILE_NAME
import com.example.chickenmasala.util.Constants.RECIPES_CSV_FILE_NAME

class HomeFragment : BaseFragment<HomeFragmentBinding>(), CategoryInteractionListener,
    SweetTreatsListener {
    override val LOG_TAG: String = "HomeFragment"
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var csvCategoryParser: CategoryParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity>
    private val foodKitchenCategoryFragment = FoodKitchenCategoryFragment()
    lateinit var getAListOfRandomRecipesInteractor: GetAListOfRandomRecipesInteractor
    private val sweetRecipeFragment = SweetRecipeFragment()

    lateinit var recipesAdapter: RecipesAdapter
    lateinit var categotyAdapter: CategotyAdapter
    lateinit var sweetAdapter: SweetAdapter


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> HomeFragmentBinding =
        HomeFragmentBinding::inflate


    override fun setup() {
        setupDataRecipesEntity()
        setupDataCategoryEntity()
        setNavigationTitleAppBar(getString(R.string.home))
        setDAtaSweetTreats()

    }


    private fun setupDataRecipesEntity() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)


        val list = getAListOfRandomRecipesInteractor.execute(5)
        recipesAdapter = RecipesAdapter(list)

    }

    private fun setDAtaSweetTreats() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)


        val list = dataSourceOfRecipeEntity.getAllItems()
            .filter { it.cleanedIngredients.toString().contains("sugar ") }.shuffled().take(5)

        sweetAdapter = SweetAdapter(list, this)

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
            recyclerSweetTreatHome.adapter = sweetAdapter


            seeAllCategories.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar(getString(R.string.food_categories))
            }
            textviewSeeAllForYou.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar(getString(R.string.for_you))
            }
            seeAllSweetTreats.setOnClickListener {
                navigationBetweenFragment(sweetRecipeFragment)
                setNavigationTitleAppBar(getString(R.string.sweet_treats))
            }
            seeAllCakeCorner.setOnClickListener {
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

    override fun onClickItemRecipeEntity(recipeEntity: String) {
        val foodDetailsFragment = FoodDetailsFragment.newInstance(recipeEntity)
        navigationBetweenParentFragment(foodDetailsFragment)

    }


}






