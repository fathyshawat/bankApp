package com.bank.currency.presentation.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bank.currency.domain.entity.HistoryItem
import com.bank.currency.domain.request.HistoryRequest
import com.bank.currency.domain.usecases.HistoryUseCase
import com.bank.currency.network.Resource
import com.bank.curreny.resourceProvider.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyUseCase: HistoryUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _historyModel = MutableSharedFlow<Resource<List<HistoryItem?>>>()
    val historyModel: SharedFlow<Resource<List<HistoryItem?>>>
        get() = _historyModel

    init {
        getHistoryList()
    }

    private fun getHistoryList() {
        historyUseCase(
            HistoryRequest(
                savedStateHandle["base"] ?: "",
                savedStateHandle["target"] ?: ""
            )
        )
            .onEach {
                _historyModel.emit(it)
            }.launchIn(viewModelScope)
    }


}