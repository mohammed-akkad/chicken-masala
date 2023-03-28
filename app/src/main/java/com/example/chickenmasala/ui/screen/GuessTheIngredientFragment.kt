package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentGuessTheIngredientBinding


class GuessTheIngredientFragment : BaseFragment<FragmentGuessTheIngredientBinding>() {
    override val LOG_TAG: String = "GuessTheIngredientFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheIngredientBinding = FragmentGuessTheIngredientBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {

    }


}