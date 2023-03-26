package com.example.chickenmasala.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentSearchFoodBinding


class SearchFoodFragment : BaseFragment<FragmentSearchFoodBinding>() {


    override val LOG_TAG: String = "SearchFoodFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchFoodBinding =
        FragmentSearchFoodBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {
    }
}