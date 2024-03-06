package com.bank.currency.data.remote

import com.bank.currency.data.response.RatingResponse
import com.bank.currency.utils.Headers.Keys.ACCESS_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyServiceApi {

    @GET("latest?access_key=${ACCESS_KEY}")
    suspend fun getLatestRates(): RatingResponse

    @GET("{date}?access_key=${ACCESS_KEY}")
    suspend fun getHistoryRates(@Path("date") date: String): RatingResponse


}