package com.jpedrodr.codewars.lib.network

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.network.exceptions.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

class ConnectivityInterceptor : Interceptor, Tagged {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val invocation = request.tag(Invocation::class.java)
        val method = invocation?.method()

        // TODO check for connectivity
        if (false) { // doesn't have internet
            logger.d(
                TAG,
                "Can't perform request due to missing internet connectivity | method=$method"
            )
            throw NoNetworkException("Can't perform request due to missing internet connectivity | request=$request")
        }

        return chain.proceed(request)
    }

}