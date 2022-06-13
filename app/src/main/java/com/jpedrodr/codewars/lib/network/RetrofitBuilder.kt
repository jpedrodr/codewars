package com.jpedrodr.codewars.lib.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.codewars.com/api/v1/users/g964/code-challenges/"

class RetrofitBuilder {

    companion object Factory {
        private val client = OkHttpClient.Builder().build()

        private val gson = GsonBuilder().create()

        fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}