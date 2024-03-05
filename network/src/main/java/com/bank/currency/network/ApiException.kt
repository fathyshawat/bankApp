package com.bank.currency.network

import android.util.Log
import com.bank.currency.model.GeneralNetworkModel
import com.bank.currency.utils.ApiException
import com.bank.curreny.resourceProvider.resProvider.IResourceProvider


fun createApiException(
    resourceProvider: IResourceProvider,
    errorResponse: GeneralNetworkModel?
): ApiException.ApiError {
    return ApiException.ApiError(
        message = errorResponse?.error?.info
            ?: (resourceProvider.getText(R.string.general_network_error)),
        statusCode = errorResponse?.error?.code.toString()
    )
}



