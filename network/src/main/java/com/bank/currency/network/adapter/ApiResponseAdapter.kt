package com.bank.currency.network.adapter

import com.bank.currency.model.GeneralNetworkModel
import com.bank.currency.network.NetworkResponseDelegate
import com.bank.curreny.resourceProvider.resProvider.IResourceProvider
import java.lang.reflect.Type
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter

class ApiResponseAdapter<T : Any>(
  private val successType: Type,
  private val resourceProvider: IResourceProvider,
  private val errorConverter: Converter<ResponseBody, GeneralNetworkModel>
) : CallAdapter<T, Call<T>> {
  override fun responseType(): Type = successType

  override fun adapt(call: Call<T>): Call<T> = NetworkResponseDelegate(call, errorConverter, resourceProvider)
}