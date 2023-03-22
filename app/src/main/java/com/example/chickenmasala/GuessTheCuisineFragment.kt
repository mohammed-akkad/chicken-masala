package com.example.chickenmasala

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
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