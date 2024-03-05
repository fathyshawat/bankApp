package com.bank.currency.utils

import com.bank.currency.data.response.Rates


fun convertCurrency(sourceRate: Double, targetRate: Double, amount: Double): Double {

    val amountInEURO = amount / sourceRate
    return amountInEURO * targetRate
}


object RatesConverter {
    fun convertToPairList(rates: Rates): List<Pair<String, Double>> {
        val pairList = mutableListOf<Pair<String, Double>>()
        val fields = Rates::class.java.declaredFields

        for (field in fields) {
            field.isAccessible = true
            val key = field.name
            val value = field.getDouble(rates)
            pairList.add(key to value)
        }

        return pairList
    }
}