package com.example.chickenmasala.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.interactors.GetAListOfRandomRecipesInteractor
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentForYouBinding
import com.example.chickenmasala.ui.adapter.ForYouRecipesAdapter
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.util.Constants


class ForYouFragment : BaseFragment<FragmentForYouBinding>() , RecipeInteractionListener {
    override val LOG_TAG: String = "FragmentForYou"
    private lateinit var csvRecipeParser: RecipeParser
    private val foodDetailsFragment = FoodDetailsFragment()
    private val bundle= Bundle()
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
    lateinit var getAListOfRandomRecipesInteractor: GetAListOfRandomRecipesInteractor
    lateinit var forYouAdapter : ForYouRecipesAdapter


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentForYouBinding =
        FragmentForYouBinding::inflate
    override fun setup() {
        setupDataRecipesEntity()


    }

    private fun setupDataRecipesEntity() {
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)

        getAListOfRandomRecipesInteractor =
            GetAListOfRandomRecipesInteractor(dataSourceOfRecipeEntity)


        val list = arguments?.getParcelableArrayList<RecipeEntity>("key")!!.toList()
        val listAllForYou = getAListOfRandomRecipesInteractor.execute(45)
        forYouAdapter = ForYouRecipesAdapter(list+listAllForYou , this)

    }

    override fun addCallBacks() {
        binding.apply {
            recyclerForYouHome.adapter = forYouAdapter
        }
    }


    override fun onClickItemRecipeEntitty(recipeEntity: RecipeEntity) {
        navigationBetweenFragment(foodDetailsFragment)

        bundle.putString("name" , recipeEntity.name)
        bundle.putString("cleanedIngredients" , recipeEntity.cleanedIngredients.joinToString())
        bundle.putString("imageUrl" , recipeEntity.imageUrl)
        bundle.putString("ingredients" , recipeEntity.ingredients.joinToString { it })
        foodDetailsFragment.arguments = bundle
    }



    private fun navigationBetweenFragment(fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }


    }