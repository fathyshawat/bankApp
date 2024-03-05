package com.bank.currency.domain.repository

import com.bank.currency.data.remote.CurrencyServiceApi
import com.bank.currency.data.response.RatingResponse
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.domain.mapper.RatingMapper
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(
    private val currencyServiceApi: CurrencyServiceApi,
    private val ratingMapper: RatingMapper
) : CurrencyRepository {
    override suspend fun getRates(): RatingModel =
        currencyServiceApi.getLatestRates().run {
            ratingMapper.map(this)
        }
}