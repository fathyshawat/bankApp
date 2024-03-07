package com.bank.currency

import com.bank.currency.domain.entity.HistoryModel
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.domain.repository.CurrencyRepository

class FakeCurrencyRepository : CurrencyRepository {
    override suspend fun getRates() = RatingModel(
        listOf(
            Pair("EUR", 1.0)
        )
    )

    override suspend fun getHistoryRates(date: String) =
        HistoryModel(
            date = "10-10-2020",
            ratingList = listOf(
                Pair("EUR", 1.0),
                Pair("USD", 1.0),
                Pair("EGP", 1.0)
            ),
            base = "USD",
            target = "EGP"
        )
}