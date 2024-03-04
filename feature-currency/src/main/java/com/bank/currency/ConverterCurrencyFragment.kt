package com.bank.currency

import android.os.Bundle
import android.view.View
import com.bank.currency.databinding.ConverterFragmentBinding
import com.bank.curreny.resourceProvider.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConverterCurrencyFragment :
    BaseFragment<ConverterFragmentBinding>(ConverterFragmentBinding::inflate) {
    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {

    }


}