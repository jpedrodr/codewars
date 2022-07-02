package com.jpedrodr.codewars.lib.network

import com.google.gson.GsonBuilder
import com.jpedrodr.codewars.lib.repository.OfflineModeRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.codewars.com/api/v1/users/g964/code-challenges/"

/**
 * Class responsible for instantiating Retrofit
 */
class RetrofitBuilder {

    companion object Factory {
        private fun getClient(offlineModeRepository: OfflineModeRepository) = OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor(offlineModeRepository))
            .build()

        private val gson = GsonBuilder().create()

        fun getRetrofit(offlineModeRepository: OfflineModeRepository): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient(offlineModeRepository))
            .addCallAdapterFactory(CustomCallFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}