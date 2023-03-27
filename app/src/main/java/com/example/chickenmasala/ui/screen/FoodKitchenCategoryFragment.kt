package com.example.chickenmasala.ui.screen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.databinding.FragmentFoodKitchenCategoryBinding
import com.example.chickenmasala.ui.adapter.AllCategoryAdapter
import com.example.chickenmasala.util.Constants


class FoodKitchenCategoryFragment : BaseFragment<FragmentFoodKitchenCategoryBinding>() {
    override val LOG_TAG: String = "FoodKitchenCategoryFragment"
    private lateinit var csvCategoryParser: CategoryParser
    private lateinit var dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity>
    lateinit var allCategoryAdapter: AllCategoryAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodKitchenCategoryBinding =
        FragmentFoodKitchenCategoryBinding::inflate



    override fun setup() {

        setupDateAllCategory()

    }

    private fun setupDateAllCategory() {
        csvCategoryParser = CategoryParser()
        dataSourceOfCategoryEntity =
            CsvDataSource(requireContext(), Constants.CATEGORIES_CSV_FILE_NAME, csvCategoryParser)


        val list = dataSourceOfCategoryEntity.getAllItems().shuffled()
        Log.d(LOG_TAG, "$list")
        allCategoryAdapter = AllCategoryAdapter(list)

    }

    override fun addCallBacks() {
        binding.apply {
            itemCard.apply {

                adapter = allCategoryAdapter
            }
        }

    }










}