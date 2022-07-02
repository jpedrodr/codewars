package com.jpedrodr.codewars.app.network

import com.jpedrodr.codewars.lib.network.Error
import com.jpedrodr.codewars.lib.network.Result
import com.jpedrodr.codewars.lib.network.Success
import com.jpedrodr.codewars.lib.network.exceptions.NoNetworkException
import retrofit2.Response


inline fun <V, B : Any> performNetwork(
    body: B,
    request: (B) -> Response<V>
): Result<V> {
    val response: Response<V>

    try {
        request(body)
    } catch (e: NoNetworkException) {

    }

    try {
        response = request(body)
    } catch (throwable: Exception) {
        return Error(throwable)
    }

    return if (response.isSuccessful) {
        val responseBody = response.body()

        if (responseBody == null) {
            Error(IllegalStateException("Response body cannot be null"))
        } else {
            Success(responseBody)
        }
    } else {
        Error(IllegalStateException("Request was not successful"))
    }
}