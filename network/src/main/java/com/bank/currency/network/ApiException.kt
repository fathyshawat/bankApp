package com.bank.currency.network

import com.bank.currency.model.GeneralNetworkModel
import com.bank.currency.utils.ApiException
import com.bank.curreny.resourceProvider.resProvider.IResourceProvider


fun createApiException(
    resourceProvider: IResourceProvider,
    errorResponse: GeneralNetworkModel
): ApiException.ApiError {
    return ApiException.ApiError(
        message = errorResponse.error?.message
            ?: resourceProvider.getText(R.string.general_network_error),
        statusCode = errorResponse.error?.txnResponseCode ?: "0"
    )
}



