package com.bank.currency.domain.request

data class HistoryRequest(
    val base: String,
    val target: String
)
