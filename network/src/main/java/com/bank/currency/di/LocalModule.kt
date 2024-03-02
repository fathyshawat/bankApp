package com.bank.currency.di


import com.bank.curreny.resourceProvider.resProvider.IResourceProvider
import com.bank.curreny.resourceProvider.resProvider.ResourceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {

  @Binds
  abstract fun bindTextProvider(textProvider: ResourceProvider): IResourceProvider
}