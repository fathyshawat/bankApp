package com.bank.currency.presentation.main_conventer

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.bank.currency.databinding.ConverterFragmentBinding
import com.bank.currency.domain.entity.Currencies
import com.bank.currency.network.Resource
import com.bank.curreny.resourceProvider.base.BaseFragment
import com.bank.curreny.resourceProvider.extenstions.collect
import com.bank.curreny.resourceProvider.extenstions.init
import com.bank.curreny.resourceProvider.extenstions.navigateSafe
import com.bank.curreny.resourceProvider.extenstions.showMessageDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ConverterCurrencyFragment :
    BaseFragment<ConverterFragmentBinding>(ConverterFragmentBinding::inflate) {

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        observer()
    }

    private fun initViews() {
        viewBinding().apply {
            spFrom.init(requireActivity(), viewModel.ratingFromListKey)
            spTo.init(requireActivity(), viewModel.ratingToListKey)
            imgExchange.setOnClickListener {
                viewModel.swipeValues()
                setSpinner()
                tvAmountTo.text = viewModel
                    .convertAmountCurrency(
                        viewBinding().etAmountFrom.text.toString().toDoubleOrNull() ?: 0.0
                    ).toString()
            }
            btnDetails.setOnClickListener {
                navigateSafe(
                    ConverterCurrencyFragmentDirections.actionConverterFragmentToHistoryFragment(
                        viewModel.ratingFromListKey[viewModel.fromCurrencyRatePos],
                        viewModel.ratingToListKey[viewModel.toCurrencyRatePos],
                        Currencies(
                            viewModel.mainCurrencies
                        ),
                        viewModel.ratingFromListValue[viewModel.fromCurrencyRatePos].toString()
                    )
                )
            }
        }
    }

    private fun observer() {
        collect(viewModel.rateModel) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    viewModel.spiltListCurrency(it.value?.ratingList)
                    setSpinner()
                    setCurrencyText(viewBinding().etAmountFrom.text.
                    toString().toDoubleOrNull() ?: 0.0)
                }

                is Resource.Failure -> {
                    hideLoading()
                    showMessageDialog(it.exception.message ?: "")
                }

                else -> showLoading()
            }
        }
        viewBinding().apply {
            spFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.fromCurrencyRatePos = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            spTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.toCurrencyRatePos = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            etAmountFrom.addTextChangedListener {
                val amount = it.toString().toDoubleOrNull() ?: 0.0
                setCurrencyText(amount)
            }

        }
    }

    private fun setCurrencyText(amount: Double) {
        viewBinding().tvAmountTo.text = viewModel.convertAmountCurrency(amount).toString()
    }
    private fun setSpinner() {
        viewBinding().spFrom.setSelection(viewModel.fromCurrencyRatePos)
        viewBinding().spTo.setSelection(viewModel.toCurrencyRatePos)
    }

}