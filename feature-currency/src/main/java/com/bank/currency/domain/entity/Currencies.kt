package com.bank.currency.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currencies(var currencies: List<CurrencyItem?>) : Parcelable

@Parcelize
data class CurrencyItem(var currencies: String, var rate: Double) : Parcelable
