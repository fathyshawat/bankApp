package com.bank.curreny.resourceProvider.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(val bindingFactory: (LayoutInflater) -> VB) :
    AppCompatActivity() {


    private var _viewBinding: VB? = null
    var navController: NavController? = null

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

    override fun onSupportNavigateUp(): Boolean {
        if (!(navController?.navigateUp() == true || super.onSupportNavigateUp())) {
            onBackPressedDispatcher.onBackPressed()
        }
        return true
    }

    fun setUpNavController(navContainerId: Int, activity: AppCompatActivity) {
        val navHostFragment = activity.supportFragmentManager.findFragmentById(
            navContainerId
        ) as NavHostFragment

        navHostFragment.navController.apply {
            navController = this
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }


}