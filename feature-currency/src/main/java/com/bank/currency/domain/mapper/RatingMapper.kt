package com.bank.currency.domain.mapper

import com.bank.currency.data.response.RatingResponse
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.mappers.BaseMapper
import com.bank.currency.utils.convertToPairList
import javax.inject.Inject


class RatingMapper @Inject constructor() : BaseMapper<RatingResponse, RatingModel> {
    override fun map(model: RatingResponse): RatingModel = with(model) {
        val ratesList = convertToPairList(this.rates)
        RatingModel(ratingList = ratesList)
    }
}