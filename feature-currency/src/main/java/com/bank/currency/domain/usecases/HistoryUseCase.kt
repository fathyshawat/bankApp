package com.bank.currency.domain.usecases

import com.bank.currency.coroutine_dispatchers.IoDispatcher
import com.bank.currency.domain.entity.HistoryItem
import com.bank.currency.domain.mapper.HistoryItemMapper
import com.bank.currency.domain.repository.CurrencyRepository
import com.bank.currency.domain.request.HistoryRequest
import com.bank.currency.usecases.FlowUseCase
import com.bank.currency.utils.getPreviousThreeDays
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HistoryUseCase @Inject constructor(
    private val repository: CurrencyRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val mapper: HistoryItemMapper
) : FlowUseCase<HistoryRequest, MutableList<HistoryItem?>>(ioDispatcher) {
    override fun execute(parameters: HistoryRequest): Flow<MutableList<HistoryItem?>> = flow {

        val response: MutableList<HistoryItem?> = mutableListOf()
        getPreviousThreeDays().asFlow().collect { date ->
            response.add(repository.getHistoryRates(date).run {
                this.base = parameters.base
                this.target = parameters.target
                mapper.map(this)
            })
            response.sortByDescending { it?.date }
            emit(response)

        }
    }
}