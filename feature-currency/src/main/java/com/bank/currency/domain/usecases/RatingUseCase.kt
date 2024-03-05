package com.bank.currency.domain.usecases

import com.bank.currency.coroutine_dispatchers.IoDispatcher
import com.bank.currency.data.response.RatingResponse
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.domain.repository.CurrencyRepository
import com.bank.currency.usecases.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RatingUseCase @Inject constructor(
    private val repository: CurrencyRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    FlowUseCase<Unit, RatingModel?>(ioDispatcher) {
    override fun execute(parameters: Unit):
            Flow<RatingModel?> = flow {
        emit(repository.getRates())
    }
}