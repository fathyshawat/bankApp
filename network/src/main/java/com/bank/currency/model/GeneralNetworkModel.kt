package com.bank.currency.model

import com.google.gson.annotations.SerializedName

data class GeneralNetworkModel(
    @SerializedName("error")
    val error: ErrorDataModel?
)

class ErrorDataModel(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("code")
    val txnResponseCode: String? = null,
)