package com.bank.currency.domain.mapper

import com.bank.currency.domain.entity.HistoryItem
import com.bank.currency.domain.entity.HistoryModel
import com.bank.currency.mappers.BaseMapper
import com.bank.currency.utils.convertCurrency
import javax.inject.Inject

class HistoryItemMapper @Inject constructor() : BaseMapper<HistoryModel, HistoryItem> {
    override fun map(model: HistoryModel): HistoryItem = with(model) {
        var baseRate = 0.0
        var targetRate = 0.0

        ratingList?.let {
            for (pair in it) {
                if (pair.first == this.base) baseRate = pair.second
                if (pair.first == this.target) targetRate = pair.second
            }
        }
        HistoryItem(
            date = model.date,
            amount = convertCurrency(baseRate, targetRate)
        )
    }
}