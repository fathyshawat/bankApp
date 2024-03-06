package com.bank.currency.domain.entity

data class HistoryModel(
    val date: String?=null,
    val ratingList: List<Pair<String, Double>>? = null,
    var base: String? = null,
    var target: String? = null

)