package com.jpedrodr.codewars.lib.network

import android.util.Log
import com.jpedrodr.codewars.lib.network.exceptions.NoNetworkException
import com.jpedrodr.codewars.lib.repository.OfflineModeRepository
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class ConnectivityInterceptor(private val offlineModeRepository: OfflineModeRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        val invocation = request.tag(Invocation::class.java)
        val method = invocation?.method()

        // TODO check for connectivity
        if (offlineModeRepository.isOffline()) { // doesn't have internet
            Log.d(
                "ConnectivityInterceptor",
                "Can't perform request due to missing internet connectivity | method=$method"
            )
            throw NoNetworkException("Can't perform request due to missing internet connectivity | request=$request")
        }

        return chain.proceed(request)
    }

}