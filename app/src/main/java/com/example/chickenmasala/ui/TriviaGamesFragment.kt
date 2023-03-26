package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.FragmentTriviaGamesBinding


class TriviaGamesFragment : BaseFragment<FragmentTriviaGamesBinding>() {
    override val LOG_TAG: String = "TriviaGamesFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTriviaGamesBinding =
        FragmentTriviaGamesBinding::inflate


    override fun setup() {
    }

    override fun addCallBacks() {
    }

}