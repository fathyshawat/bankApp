package com.bank.currency.presentation.history

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bank.currency.databinding.ItemCurrenciesBinding
import com.bank.currency.domain.entity.CurrencyItem
import com.bank.currency.utils.convertCurrency

class CommonCurrencyAdapter(private val sourceRate: Double) :
    ListAdapter<CurrencyItem, CommonCurrencyAdapter.CurrenciesViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CurrencyItem>() {
            override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
                return oldItem.currencies == newItem.currencies
            }

            override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        val binding =
            ItemCurrenciesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrenciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        val currencyItem = getItem(position)
        holder.bind(sourceRate, currencyItem)
    }

    class CurrenciesViewHolder(private val binding: ItemCurrenciesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sourceRate: Double, currencyItem: CurrencyItem) {
            binding.apply {
                currency.text = convertCurrency(sourceRate, currencyItem.rate).toString()
                currencyType.text=currencyItem.currencies
            }
        }
    }
}