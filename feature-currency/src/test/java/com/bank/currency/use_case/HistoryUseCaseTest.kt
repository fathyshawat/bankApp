@file:OptIn(ExperimentalCoroutinesApi::class)

package com.bank.currency.use_case

import com.bank.currency.FakeCurrencyRepository
import com.bank.currency.domain.entity.HistoryItem
import com.bank.currency.domain.request.HistoryRequest
import com.bank.currency.domain.usecases.HistoryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class HistoryUseCaseTest {


    private lateinit var getHistoryUseCase: HistoryUseCase
    private lateinit var fakeCurrencyRepository: FakeCurrencyRepository

    @Before
    fun setUp() {
        fakeCurrencyRepository = FakeCurrencyRepository()
    }

    @Test
    fun test_get_history_rates() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        getHistoryUseCase = HistoryUseCase(
            fakeCurrencyRepository, dispatcher
        )
        val historyRequest = HistoryRequest("USD", "EGP")
        val response =
            HistoryItem("10-10-2020", 1.0)

        val result = getHistoryUseCase.execute(historyRequest).first()
        assertThat(result, equalTo(response))
    }
}