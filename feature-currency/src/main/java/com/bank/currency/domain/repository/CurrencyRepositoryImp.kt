package com.bank.currency.domain.repository

import com.bank.currency.data.remote.CurrencyServiceApi
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.domain.mapper.mapToHistoryModel

import com.bank.currency.domain.mapper.mapToRatingModel
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(
    private val currencyServiceApi: CurrencyServiceApi,

) : CurrencyRepository {
    override suspend fun getRates(): RatingModel =
        currencyServiceApi.getLatestRates().run {
            mapToRatingModel()
        }

    override suspend fun getHistoryRates(date: String) =
        currencyServiceApi.getHistoryRates(date).run {
            mapToHistoryModel()
        }
}