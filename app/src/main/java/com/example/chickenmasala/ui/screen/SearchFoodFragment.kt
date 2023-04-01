package com.example.chickenmasala.ui.screen


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentSearchFoodBinding
import com.example.chickenmasala.ui.adapter.RecipeHorizontalAdapter
import com.example.chickenmasala.ui.listener.RecipeInteractionListener
import com.example.chickenmasala.util.Constants
import com.google.android.material.bottomsheet.BottomSheetDialog


class SearchFoodFragment : BaseFragment<FragmentSearchFoodBinding>(), RecipeInteractionListener {


    override val LOG_TAG: String = "SearchFoodFragment"
    lateinit var dialog: BottomSheetDialog
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchFoodBinding =
        FragmentSearchFoodBinding::inflate

    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
     var searchAdapter = RecipeHorizontalAdapter(emptyList(),this)

    override fun setup() {
        createBottomSheetDialog()

    }

    override fun addCallBacks() {
    }


    private fun setupDateSearchItem() {
        val nameRecipe = binding.searchTextView.text.toString()
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)
        if (nameRecipe.isNotEmpty()) {
            val list = dataSourceOfRecipeEntity.getAllItems()
                .filter { it.name.contains("$nameRecipe ") }
            searchAdapter = RecipeHorizontalAdapter(list,this)
            if (list.isNotEmpty()) {
                binding.searchResultsRecycler.visibility = View.VISIBLE
                binding.recipeNotFoundView.visibility = View.GONE
                Log.d(LOG_TAG, "$list")
            } else {
                validationItem()
            }
        } else {
            validationItem()

        }


    }

    private fun validationItem() {
        binding.searchResultsRecycler.visibility = View.GONE
        binding.recipeNotFoundView.visibility = View.VISIBLE

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createBottomSheetDialog() {
        dialog = BottomSheetDialog(binding.root.context)
        binding.searchTextView.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    val drawableRight = 2
                    val drawableLeft = 0
                    val rightDrawableWidth =
                        binding.searchTextView.compoundDrawables[drawableRight].bounds.width()
                    val leftDrawableWidth =
                        binding.searchTextView.compoundDrawables[drawableLeft].bounds.width()
                    val x = motionEvent.rawX.toInt()
                    when {
                        x >= binding.searchTextView.right - rightDrawableWidth -> {
                            setupDateSearchItem()
                            binding.searchResultsRecycler.adapter = searchAdapter
                            return@setOnTouchListener true
                        }
                        x <= binding.searchTextView.left + leftDrawableWidth -> {
                            createDialog()
                            dialog.show()
                            return@setOnTouchListener true
                        }
                    }
                }
            }
            return@setOnTouchListener false
        }
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }


    private fun createDialog() {
        val view: View = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null, false)
        dialog.dismiss()
        dialog.setContentView(view)


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