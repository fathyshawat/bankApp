package com.bank.curreny.resourceProvider.resProvider.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity() {


    private var _viewBinding: VB? = null

    abstract fun onActivityCreated(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _viewBinding = bindingFactory(layoutInflater)
        setContentView(viewBinding().root)

        if (savedInstanceState == null) {
            onActivityCreated(savedInstanceState)
        } else {
            //activity recreated
        }
    }


    private fun viewBinding(): VB {
        if (_viewBinding == null) throw NullPointerException("viewBinding is null!!")
        return _viewBinding!!
    }


    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}