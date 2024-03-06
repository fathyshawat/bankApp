package com.bank.currency.utils

import android.annotation.SuppressLint
import android.util.Log
import com.bank.currency.data.response.Rates
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.math.log


fun convertCurrency(sourceRate: Double, targetRate: Double, amount: Double = 1.00): Double {
    val amountInEURO = amount / sourceRate
    return amountInEURO * targetRate
}

fun convertToPairList(rates: Rates): List<Pair<String, Double>> {
    val pairList = mutableListOf<Pair<String, Double>>()
    val fields = Rates::class.java.declaredFields

    for (field in fields) {
        field.isAccessible = true
        val key = field.name.uppercase()
        val value = field.getDouble(rates)
        pairList.add(key to value)
    }

    return pairList
}

@SuppressLint("SimpleDateFormat")
fun getPreviousThreeDays(): List<String> {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val formattedDates = mutableListOf<String>()
    for (i in 0..2) {
        cal.add(Calendar.DAY_OF_YEAR, -1)
        formattedDates.add(sdf.format(cal.time))
        Log.d("TAGDate", "getPreviousThreeDays: "+sdf.format(cal.time))

    }

    return formattedDates.toList()
}
