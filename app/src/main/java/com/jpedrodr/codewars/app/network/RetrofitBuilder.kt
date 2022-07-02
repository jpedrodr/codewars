package com.jpedrodr.codewars.app.network

import com.google.gson.GsonBuilder
import com.jpedrodr.codewars.app.network.ConnectivityInterceptor
import com.jpedrodr.codewars.lib.network.CustomCallFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.codewars.com/api/v1/users/g964/code-challenges/"

/**
 * Class responsible for instantiating Retrofit
 */
class RetrofitBuilder {

    companion object Factory {
        private val client = OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor())
            .build()

        private val gson = GsonBuilder().create()

        fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(CustomCallFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}