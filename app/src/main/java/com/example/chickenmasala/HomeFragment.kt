package com.example.chickenmasala

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment<HomeFragmentBinding>() {
    override val LOG_TAG: String = "HomeFragment"

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> HomeFragmentBinding =
        HomeFragmentBinding::inflate


    override fun setup() {
    }

    override fun addCallBacks() {
    }


}