package com.bank.currency.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bank.currency.databinding.HistoryFragmentBinding
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

    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {
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
        historyAdapter.submitList(list)
        viewBinding().historyRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter

        }
    }

}