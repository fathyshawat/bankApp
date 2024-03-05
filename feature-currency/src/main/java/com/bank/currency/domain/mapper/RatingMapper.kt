package com.bank.currency.domain.mapper

import com.bank.currency.data.response.RatingResponse
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.mappers.BaseMapper
import com.bank.currency.utils.RatesConverter
import javax.inject.Inject


class RatingMapper @Inject constructor() : BaseMapper<RatingResponse, RatingModel> {
    override fun map(model: RatingResponse): RatingModel = with(model) {
        val ratesList = RatesConverter.convertToPairList(this.rates)
        RatingModel(ratingList = ratesList)
    }
}