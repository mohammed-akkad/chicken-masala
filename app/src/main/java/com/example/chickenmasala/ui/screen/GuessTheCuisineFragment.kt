package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentGuessTheCuisineBinding


class GuessTheCuisineFragment : BaseFragment<FragmentGuessTheCuisineBinding>() {
    override val LOG_TAG: String = "GuessTheCuisineFrag"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheCuisineBinding =
        FragmentGuessTheCuisineBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {
    }


}