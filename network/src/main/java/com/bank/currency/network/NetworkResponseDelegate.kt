package com.bank.currency.network

import android.util.Log
import com.bank.currency.model.GeneralNetworkModel
import com.bank.currency.utils.ApiException
import com.bank.curreny.resourceProvider.resProvider.IResourceProvider
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response

internal class NetworkResponseDelegate<T : Any>(
  private val delegate: Call<T>,
  private val errorConverter: Converter<ResponseBody, GeneralNetworkModel>,
  private val resourceProvider: IResourceProvider
) : Call<T> by delegate {

  override fun enqueue(callback: Callback<T>) = delegate.enqueue(object : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
      if (response.isSuccessful) {
        callback.onResponse(this@NetworkResponseDelegate, Response.success(response.body()))
      } else {
        callback.onFailure(
          this@NetworkResponseDelegate,
          handleApiException(resourceProvider, response.code(), response.errorBody())
        )
      }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
      if (BuildConfig.DEBUG) {
        Log.e("onFailure (API) ---> ", "${t.message}")
      }
      callback.onFailure(this@NetworkResponseDelegate, parseApiException(t))
    }
  })

  override fun clone(): Call<T> = NetworkResponseDelegate(delegate.clone(), errorConverter, resourceProvider)

  private fun parseApiException(exception: Throwable): ApiException =
    when (exception) {
      is ConnectException,
      is SocketTimeoutException,
      is UnknownHostException
      -> {
        ApiException.NoInternetConnection(
          message = resourceProvider.getText(R.string.no_internet_error_message),
          throwable = exception
        )
      }

      is HttpException -> {
        handleApiException(resourceProvider, exception.code(), exception.response()?.errorBody())
      }

      else -> {
        ApiException.GeneralError(exception.message)
      }
    }

  private fun handleApiException(
    resourceProvider: IResourceProvider,
    code: Int,
    errorBody: ResponseBody?
  ): ApiException = when (code) {
    HttpURLConnection.HTTP_BAD_GATEWAY -> ApiException.GeneralError(
      resourceProvider.getText(R.string.general_network_error)
    )

    HttpURLConnection.HTTP_UNAUTHORIZED -> ApiException.Unauthorized

    HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> {
      errorBody?.let {
        try {
          val errorResponse = errorConverter.convert(it) ?: stateException("Can't serialize response")
          ApiException.TimeOut(resourceProvider.getText(R.string.server_timeout_message))
        } catch (e: Throwable) {
          if (BuildConfig.DEBUG) {
            Log.e("API Failed with: ", "$e")
          }
          ApiException.ApiError()
        }
      } ?: ApiException.GeneralError(resourceProvider.getText(R.string.general_network_error))
    }

    else -> {
      errorBody?.let {
        try {
          val errorResponse = errorConverter.convert(it) ?: stateException("No Error body")
          createApiException(resourceProvider, errorResponse)
        } catch (e: Throwable) {
          if (BuildConfig.DEBUG) {
            Log.e("API Failed with: ", "$e")
          }
          ApiException.ApiError()
        }
      } ?: ApiException.GeneralError(resourceProvider.getText(R.string.general_network_error))
    }
  }
}