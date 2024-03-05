package com.bank.currency.data.response

import com.bank.currency.model.GeneralNetworkModel
import com.google.gson.annotations.SerializedName

class RatingResponse(

    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Rates,
    @SerializedName("timestamp") val timestamp: Int,
    @SerializedName("base") var base: String

) : GeneralNetworkModel()


