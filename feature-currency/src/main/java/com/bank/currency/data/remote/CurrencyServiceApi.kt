package com.bank.currency.data.remote

import com.bank.currency.data.response.RatingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyServiceApi {

    @GET("latest?access_key=85c8ec85e31a695773310e78d2fff34e")
    suspend fun getLatestRates(): RatingResponse
}