package com.bank.currency.di


import com.bank.currency.data.remote.CurrencyServiceApi
import com.bank.currency.domain.repository.CurrencyRepository
import com.bank.currency.domain.repository.CurrencyRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class SparkRepoModule {
  @Binds
  abstract fun bindCurrencyRepository(repository: CurrencyRepositoryImp): CurrencyRepository
}

@Module
@InstallIn(SingletonComponent::class)
object SparkModule {

  @Provides
  @Singleton
  fun provideApiService(
    @Annotations.CoreNetwork
    retrofit: Retrofit
  ): CurrencyServiceApi = retrofit.create(
    CurrencyServiceApi::class.java
  )
}