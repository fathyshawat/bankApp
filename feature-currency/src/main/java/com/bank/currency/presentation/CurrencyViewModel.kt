package com.bank.currency.presentation

import androidx.lifecycle.viewModelScope
import com.bank.currency.R
import com.bank.currency.domain.entity.RatingModel
import com.bank.currency.domain.usecases.RatingUseCase
import com.bank.currency.network.Resource
import com.bank.currency.utils.convertCurrency
import com.bank.curreny.resourceProvider.base.BaseViewModel
import com.bank.curreny.resourceProvider.resProvider.IResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val ratingUseCase: RatingUseCase,
    private val iResourceProvider: IResourceProvider
) : BaseViewModel() {

    private val _rateModel = MutableSharedFlow<Resource<RatingModel?>>()
    val rateModel: SharedFlow<Resource<RatingModel?>>
        get() = _rateModel

    var ratingFromListKey: MutableList<String> = mutableListOf()
    var ratingToListKey: MutableList<String> = mutableListOf()
    private var ratingToListValue: MutableList<Double> = mutableListOf()
    private var ratingFromListValue: MutableList<Double> = mutableListOf()

    var fromCurrencyRatePos: Int = -1
    var toCurrencyRatePos: Int = -1

    init {
        ratingFromListKey.add(iResourceProvider.getText(R.string.choice))
        ratingToListKey.add(iResourceProvider.getText(R.string.choice))
        ratingFromListValue.add(0.0)
        ratingToListValue.add(0.0)
        getRates()
    }

    private fun getRates() {
        ratingUseCase(Unit)
            .onEach {
                _rateModel.emit(it)
            }.launchIn(viewModelScope)
    }




    fun spiltListCurrency(ratingList: List<Pair<String, Double>>? = null) {
        ratingList?.forEach { it ->
            ratingFromListKey.add(it.first)
            ratingToListKey.add(it.first)
            ratingFromListValue.add(it.second)
            ratingToListValue.add(it.second)
        }
        toCurrencyRatePos = ratingToListKey.indexOf("USD")
        fromCurrencyRatePos = ratingFromListKey.indexOf("EGP")
    }
    fun swipeValues() {
        val temp = fromCurrencyRatePos
        fromCurrencyRatePos = toCurrencyRatePos
        toCurrencyRatePos = temp
    }
    fun convertAmountCurrency(amount: Double): Double {
        return convertCurrency(
            ratingFromListValue[fromCurrencyRatePos],
            ratingToListValue[toCurrencyRatePos],
            amount
        )
    }

}