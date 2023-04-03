package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentAboutIndianFoodBinding

class IndianFoodHistoryFragment : BaseFragment<FragmentAboutIndianFoodBinding>() {
    override val LOG_TAG: String = IndianFoodHistoryFragment::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAboutIndianFoodBinding =
        FragmentAboutIndianFoodBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {
    }
}