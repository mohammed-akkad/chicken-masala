package com.example.chickenmasala

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB:ViewBinding>:Fragment(){
abstract val LOG_TAG:String
abstract val bindingInflater:(LayoutInflater,ViewGroup?,Boolean)->VB
    private var _binding:ViewBinding?=null
    protected val binding
    get() = _binding as VB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setup()
        addCallBacks()
        _binding=bindingInflater(inflater,container,false)
        return (_binding as VB).root

    }
    abstract fun setup()
    abstract fun addCallBacks()
    protected fun log(value: String)=Log.v(LOG_TAG,value)

}