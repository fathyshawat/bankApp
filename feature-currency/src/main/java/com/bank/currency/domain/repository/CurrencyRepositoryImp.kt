package com.bank.currency.domain.repository

import com.bank.currency.data.remote.CurrencyServiceApi
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.domain.mapper.HistoryMapper
import com.bank.currency.domain.mapper.RatingMapper
import javax.inject.Inject

class CurrencyRepositoryImp @Inject constructor(
    private val currencyServiceApi: CurrencyServiceApi,
    private val ratingMapper: RatingMapper,
    private val historyMapper: HistoryMapper
) : CurrencyRepository {
    override suspend fun getRates(): RatingModel =
        currencyServiceApi.getLatestRates().run {
            ratingMapper.map(this)
        }

    override suspend fun getHistoryRates(date: String) =
        currencyServiceApi.getHistoryRates(date).run {
            historyMapper.map(this)
        }
}