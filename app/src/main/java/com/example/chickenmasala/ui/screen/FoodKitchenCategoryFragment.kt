package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.databinding.FragmentRecyclerBinding
import com.example.chickenmasala.ui.adapter.LargeCardAdapter
import com.example.chickenmasala.util.Constants


class FoodKitchenCategoryFragment : BaseFragment<FragmentRecyclerBinding>() {
    override val LOG_TAG: String = "FoodKitchenCategoryFragment"
    private lateinit var csvCategoryParser: CategoryParser
    private lateinit var dataSourceOfCategoryEntity: CsvDataSource<CategoryEntity>
    lateinit var allCategoriesAdapter: LargeCardAdapter


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecyclerBinding =
        FragmentRecyclerBinding::inflate


    override fun setup() {
        setupDateAllCategory()
    }

    private fun setupDateAllCategory() {
        csvCategoryParser = CategoryParser()
        dataSourceOfCategoryEntity =
            CsvDataSource(requireContext(), Constants.CATEGORIES_CSV_FILE_NAME, csvCategoryParser)
        val list = dataSourceOfCategoryEntity.getAllItems().shuffled().map {
            Pair(it.name,it.imageUrl)
        }
        allCategoriesAdapter = LargeCardAdapter(list){position: Int ->  
            //TODO: Handle on category click listener
        }

    }


    override fun addCallBacks() {
        binding.apply {
            itemsRecycler.apply {
                adapter = allCategoriesAdapter
            }

        }

    }


}