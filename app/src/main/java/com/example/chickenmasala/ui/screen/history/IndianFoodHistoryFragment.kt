package com.example.chickenmasala.ui.screen.history

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentAboutIndianFoodBinding
import com.example.chickenmasala.ui.screen.base.BaseFragment

class IndianFoodHistoryFragment : BaseFragment<FragmentAboutIndianFoodBinding>() {
    override var isShowBackButton: Boolean = false
    override val LOG_TAG: String = IndianFoodHistoryFragment::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAboutIndianFoodBinding =
        FragmentAboutIndianFoodBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {
    }
}