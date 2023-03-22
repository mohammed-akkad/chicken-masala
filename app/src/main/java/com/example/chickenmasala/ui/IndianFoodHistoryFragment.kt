package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentAboutIndianFoodBinding

class IndianFoodHistoryFragment : BaseFragment<FragmentAboutIndianFoodBinding>() {
    override val LOG_TAG: String = "IndianFoodHistoryFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAboutIndianFoodBinding =
        FragmentAboutIndianFoodBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {
    }
}