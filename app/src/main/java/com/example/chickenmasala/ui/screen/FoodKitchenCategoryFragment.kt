package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chickenmasala.R
import com.example.chickenmasala.data.CsvDataSource
import com.example.chickenmasala.data.domain.CategoryEntity
import com.example.chickenmasala.data.utils.CategoryParser
import com.example.chickenmasala.databinding.FragmentRecyclerBinding
import com.example.chickenmasala.ui.adapter.LargeCardAdapter
import com.example.chickenmasala.ui.listener.CategoryInteractionListener
import com.example.chickenmasala.util.Constants


class FoodKitchenCategoryFragment : BaseFragment<FragmentRecyclerBinding>(),
    CategoryInteractionListener {
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
            Pair(it.name, it.imageUrl)
        }
        allCategoriesAdapter = LargeCardAdapter(list, this)
    }

    override fun addCallBacks() {
        binding.apply {
            itemsRecycler.apply {
                adapter = allCategoriesAdapter
            }
        }
    }

    override fun onClickItemCategory(nameCategory: String) {
        val fragment = RecipesRelatedCategoriesFragment.newInstance(nameCategory)
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
}