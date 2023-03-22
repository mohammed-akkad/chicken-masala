package com.example.chickenmasala.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.chickenmasala.databinding.KitchensFragmantBinding

class KitchenFragment: BaseFragment<KitchensFragmantBinding>() {
    override val LOG_TAG: String = "KitchenFragment"
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> KitchensFragmantBinding = KitchensFragmantBinding::inflate

    override fun setup() {
    }

    override fun addCallBacks() {
    }
}