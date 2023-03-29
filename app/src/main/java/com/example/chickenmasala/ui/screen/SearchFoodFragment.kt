package com.example.chickenmasala.ui.screen


import android.annotation.SuppressLint
import android.opengl.Visibility
import android.util.Log
import android.view.*
import android.widget.Toast
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.RecipeEntity
import com.example.chickenmasala.data.utils.RecipeParser
import com.example.chickenmasala.databinding.FragmentSearchFoodBinding
import com.example.chickenmasala.ui.adapter.SearchAdapter
import com.example.chickenmasala.util.Constants
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar


class SearchFoodFragment : BaseFragment<FragmentSearchFoodBinding>() {


    override val LOG_TAG: String = "SearchFoodFragment"
    lateinit var dialog: BottomSheetDialog
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchFoodBinding =
        FragmentSearchFoodBinding::inflate

    private lateinit var csvRecipeParser: RecipeParser
    private lateinit var dataSourceOfRecipeEntity: CsvDataSource<RecipeEntity>
     var searchAdapter = SearchAdapter(emptyList())

    override fun setup() {
        createBottomSheetDialog()

    }

    override fun addCallBacks() {
    }


    private fun setupDateSearchItem() {
        val nameRecipe = binding.editText.text.toString()
        csvRecipeParser = RecipeParser()
        dataSourceOfRecipeEntity =
            CsvDataSource(requireContext(), Constants.RECIPES_CSV_FILE_NAME, csvRecipeParser)
        if (nameRecipe.isNotEmpty()) {
            val list = dataSourceOfRecipeEntity.getAllItems()
                .filter { it.name.contains("$nameRecipe ") }
            searchAdapter = SearchAdapter(list)
            if (list.isNotEmpty()) {
                binding.recyclerSearchCard.visibility = View.VISIBLE
                binding.itemRecipeNotFound.visibility = View.GONE
                Log.d(LOG_TAG, "$list")
            } else {
                validationItem()
            }
        } else {
            Toast.makeText(binding.root.context,"Please enter recipe",Toast.LENGTH_SHORT).show()

        }


    }

    private fun validationItem() {
        binding.recyclerSearchCard.visibility = View.GONE
        binding.itemRecipeNotFound.visibility = View.VISIBLE

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createBottomSheetDialog() {
        dialog = BottomSheetDialog(binding.root.context)
        binding.editText.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    val drawableRight = 2
                    val drawableLeft = 0
                    val rightDrawableWidth =
                        binding.editText.compoundDrawables[drawableRight].bounds.width()
                    val leftDrawableWidth =
                        binding.editText.compoundDrawables[drawableLeft].bounds.width()
                    val x = motionEvent.rawX.toInt()
                    when {
                        x >= binding.editText.right - rightDrawableWidth -> {
                            setupDateSearchItem()
                            binding.recyclerSearchCard.adapter = searchAdapter
                            return@setOnTouchListener true
                        }
                        x <= binding.editText.left + leftDrawableWidth -> {
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

}