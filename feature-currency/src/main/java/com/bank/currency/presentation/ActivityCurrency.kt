package com.bank.currency.presentation

import android.os.Bundle
import com.bank.currency.R
import com.bank.currency.databinding.CurrencyMainActivityBinding
import com.bank.curreny.resourceProvider.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActivityCurrency :
    BaseActivity<CurrencyMainActivityBinding>({ CurrencyMainActivityBinding.inflate(it) }) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setUpNavController(R.id.currencyFragmentContainerView, this)
    }

}