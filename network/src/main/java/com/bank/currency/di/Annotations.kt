package com.bank.currency.di

import javax.inject.Qualifier

object Annotations {

  @Qualifier
  @Retention(AnnotationRetention.BINARY)
  annotation class CoreNetwork
}