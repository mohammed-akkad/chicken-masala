package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentGuessGameBinding

class GuessTheIngredientFragment : BaseFragment<FragmentGuessGameBinding>() {
    override val LOG_TAG: String = "GuessTheIngredientFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessGameBinding =
        FragmentGuessGameBinding::inflate
    override fun setup() {}

    override fun addCallBacks() {}

}