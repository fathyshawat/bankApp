package com.bank.currency

import android.os.Bundle
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