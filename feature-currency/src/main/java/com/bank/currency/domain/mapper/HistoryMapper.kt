package com.bank.currency.domain.mapper

import com.bank.currency.data.response.RatingResponse
import com.bank.currency.domain.entity.HistoryModel
import com.bank.currency.mappers.BaseMapper
import com.bank.currency.utils.convertToPairList
import javax.inject.Inject

class HistoryMapper @Inject constructor() : BaseMapper<RatingResponse, HistoryModel> {
    override fun map(model: RatingResponse): HistoryModel = with(model) {
        val ratesList = convertToPairList(this.rates)
        HistoryModel(
            date = this.date,
            ratingList = ratesList
        )
    }
}