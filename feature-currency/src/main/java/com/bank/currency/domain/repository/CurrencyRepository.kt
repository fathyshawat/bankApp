package com.bank.currency.domain.repository

import com.bank.currency.domain.entity.HistoryModel
import com.bank.currency.domain.entity.RatingModel

interface CurrencyRepository {

    suspend fun getRates(): RatingModel

    suspend fun getHistoryRates(date: String): HistoryModel
}