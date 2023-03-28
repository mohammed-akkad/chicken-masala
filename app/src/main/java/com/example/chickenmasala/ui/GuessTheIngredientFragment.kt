package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentGuessTheIngredientBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class GuessTheIngredientFragment : BaseFragment<FragmentGuessTheIngredientBinding>() {
    override val LOG_TAG: String = "GuessTheIngredientFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessTheIngredientBinding = FragmentGuessTheIngredientBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {

    }


}