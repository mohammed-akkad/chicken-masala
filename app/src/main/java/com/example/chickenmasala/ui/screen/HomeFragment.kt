package com.example.chickenmasala.ui.screen

import android.os.Bundle
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
import com.example.chickenmasala.databinding.FragmentHomeBinding
import com.example.chickenmasala.ui.adapter.*
import com.example.chickenmasala.ui.listener.CategoryInteractionListener
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.util.Constants
import com.example.chickenmasala.util.Constants.CATEGORIES_CSV_FILE_NAME
import com.example.chickenmasala.util.Constants.RECIPES_CSV_FILE_NAME

@Suppress("DEPRECATION")

class HomeFragment : BaseFragment<FragmentHomeBinding>(), CategoryInteractionListener,
    RecipeInteractionListener {
    override val LOG_TAG: String = "HomeFragment"
    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var csvCategoryParser: CategoryParser
    val bundle = Bundle()
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    private lateinit var dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity>
    private val foodKitchenCategoryFragment = FoodKitchenCategoryFragment()
    private val forYouRecipesFragment = ForYouFragment()
    lateinit var getAListOfRandomRecipesInteractor: GetAListOfRandomRecipesInteractor
    private val sweetRecipeFragment = SweetRecipeFragment()
    private val cakeRecipeFragment = CakeRecipeFragment()


    lateinit var sliderAdapter: SliderAdapter
    lateinit var categotyAdapter: CategotyAdapter
    lateinit var forYouAdapter: RecipeCardAdapter
    lateinit var sweetAdapter: RecipeCardAdapter
    lateinit var cakeAdapter: RecipeCardAdapter


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate


    override fun setup() {
        setupDataRecipesEntity()
        setupDataCategoryEntity()
        setNavigationTitleAppBar(getString(R.string.home))
        setDAtaSweetTreats()
        setDAtaCakeTreats()

    }


    private fun setupDataRecipesEntity() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)


        val list = getAListOfRandomRecipesInteractor.execute(5)
        val listForYou = getAListOfRandomRecipesInteractor.execute(5)
        sliderAdapter = SliderAdapter(list)
        forYouAdapter = RecipeCardAdapter(listForYou, this)
        bundle.putParcelableArrayList(Constants.KEY_RECIPES_LIST, ArrayList(list))
        forYouRecipesFragment.arguments = bundle

    }

    private fun setDAtaSweetTreats() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)


        val list = dataSourceOfRecipeEntity.getAllItems()
            .filter { it.cleanedIngredients.toString().contains("sugar ") }.shuffled().take(5)

        sweetAdapter = RecipeCardAdapter(list, this)

    }
    private fun setDAtaCakeTreats() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)


        val list = dataSourceOfRecipeEntity.getAllItems()
            .filter { it.cleanedIngredients.toString().contains("cake ") }.shuffled().take(5)

        cakeAdapter = RecipeCardAdapter(list, this)

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
            sliderRecycler.adapter = sliderAdapter
            categoriesRecycler.adapter = categotyAdapter
            sweetTreatsRecylcer.adapter = sweetAdapter
            forYouRecycler.adapter = forYouAdapter
            cakeCornerRecycler.adapter = cakeAdapter


            seeAllCategories.setOnClickListener {
                navigationBetweenFragment(foodKitchenCategoryFragment)
                setNavigationTitleAppBar(getString(R.string.food_categories))
            }
            textviewSeeAllForYou.setOnClickListener {
                navigationBetweenFragment(forYouRecipesFragment)
                setNavigationTitleAppBar(getString(R.string.for_you))
            }
            seeAllSweetTreats.setOnClickListener {
                navigationBetweenFragment(sweetRecipeFragment)
                setNavigationTitleAppBar(getString(R.string.sweet_treats))
            }
            seeAllCakeCorner.setOnClickListener {
                navigationBetweenFragment(cakeRecipeFragment)
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






