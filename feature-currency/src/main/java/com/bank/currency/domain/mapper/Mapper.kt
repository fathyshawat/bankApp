package com.bank.currency.domain.mapper

import com.bank.currency.data.response.RatingResponse
import com.bank.currency.domain.entity.HistoryItem
import com.bank.currency.domain.entity.HistoryModel
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.utils.convertCurrency
import com.bank.currency.utils.convertToPairList

fun HistoryModel.mapToHistoryItem(): HistoryItem {
    var baseRate = 0.0
    var targetRate = 0.0

    ratingList?.let {
        for (pair in it) {
            if (pair.first == this.base) baseRate = pair.second
            if (pair.first == this.target) targetRate = pair.second
        }
    }
    return HistoryItem(
        date = this.date,
        amount = convertCurrency(baseRate, targetRate)
    )
}


fun RatingResponse.mapToRatingModel(): RatingModel {
    val ratesList = convertToPairList(this.rates)
    return RatingModel(ratingList = ratesList)
}

fun RatingResponse.mapToHistoryModel(): HistoryModel {
    val ratesList = convertToPairList(this.rates)
    return HistoryModel(
        date = this.date,
        ratingList = ratesList
    )
}
