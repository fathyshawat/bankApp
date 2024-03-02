package com.bank.currency.network

fun stateException(errorMessage: String? = null): Nothing = errorMessage?.let {
  throw IllegalStateException(it)
} ?: throw IllegalStateException()

