package com.example.chickenmasala.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentGuessGameBinding
import com.example.chickenmasala.ui.screen.BaseFragment

class GuessTheCuisineFragment : BaseFragment<FragmentGuessGameBinding>() {

    override val LOG_TAG: String = "GuessTheCuisineFrag"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGuessGameBinding =
        FragmentGuessGameBinding::inflate

    override fun setup() {

    }

    override fun addCallBacks() {


    }

}