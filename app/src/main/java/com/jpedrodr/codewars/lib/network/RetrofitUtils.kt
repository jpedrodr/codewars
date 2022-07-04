package com.jpedrodr.codewars.lib.network

import retrofit2.Response

inline fun <V, B : Any> performNetwork(
    body: B,
    request: (B) -> Response<V>
): Result<V> {
    val response: Response<V>

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