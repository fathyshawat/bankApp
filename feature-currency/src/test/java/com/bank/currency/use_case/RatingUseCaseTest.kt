package com.bank.currency.use_case

import com.bank.currency.FakeCurrencyRepository
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.domain.usecases.RatingUseCase
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class RatingUseCaseTest {

    private lateinit var ratingUseCase: RatingUseCase
    private lateinit var fakeCurrencyRepository: FakeCurrencyRepository

    @Before
    fun setUp() {
        fakeCurrencyRepository = FakeCurrencyRepository()
    }

    @Test
    fun testGetHistoryRates() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        ratingUseCase = RatingUseCase(
            fakeCurrencyRepository, dispatcher
        )

        val response = RatingModel(
            ratingList = listOf(
                Pair("EUR", 1.0),
            )
        )
        val result = ratingUseCase.execute(Unit).single()
        MatcherAssert.assertThat(result, equalTo(response))
    }


}