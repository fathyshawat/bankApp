package com.bank.currency.presentation.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bank.currency.databinding.HistoryFragmentBinding
import com.bank.currency.domain.entity.CurrencyItem
import com.bank.currency.domain.entity.HistoryItem
import com.bank.currency.network.Resource
import com.bank.curreny.resourceProvider.base.BaseFragment
import com.bank.curreny.resourceProvider.extenstions.collect
import com.bank.curreny.resourceProvider.extenstions.showMessageDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HistoryFragment : BaseFragment<HistoryFragmentBinding>(HistoryFragmentBinding::inflate) {

    private val viewModel: HistoryViewModel by viewModels()
    private var sourceRate: Double = 0.0
    private var currencies: List<CurrencyItem?> = emptyList()
    private val args: HistoryFragmentArgs by navArgs()
    private lateinit var currenciesAdapter: CommonCurrencyAdapter
    private lateinit var historyAdapter: HistoryAdapter
    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {
        sourceRate = args.baseRate?.toDoubleOrNull() ?: 0.0
        currencies = args.currencies?.currencies ?: emptyList()
        initCurrenciesAdapter()
        observer()
    }

    private fun observer() {
        collect(viewModel.historyModel) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    initHistory(it.value)
                }

                is Resource.Failure -> {
                    hideLoading()
                    showMessageDialog(it.exception.message ?: "")
                }

                else -> showLoading()
            }
        }
    }

    private fun initHistory(list: List<HistoryItem?>) {
        historyAdapter = HistoryAdapter(args.target ?: "")
        historyAdapter.submitList(list)
        viewBinding().historyRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter

        }
    }

    private fun initCurrenciesAdapter() {
        currenciesAdapter = CommonCurrencyAdapter(sourceRate)
        currenciesAdapter.submitList(currencies)
        viewBinding().currenciesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = currenciesAdapter
        }
    }

}