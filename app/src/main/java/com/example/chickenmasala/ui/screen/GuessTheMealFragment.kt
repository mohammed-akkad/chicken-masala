package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentGuessTheMealBinding


class GuessTheMealFragment : BaseFragment<FragmentGuessTheMealBinding>() {
    override val LOG_TAG: String = "GuessTheMealFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheMealBinding =
        FragmentGuessTheMealBinding::inflate

    override fun setup() {

    }

    override fun addCallBacks() {
    }


}