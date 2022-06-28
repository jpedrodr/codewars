package com.jpedrodr.codewars.lib.network

import com.jpedrodr.codewars.commons.Tagged
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit

class CustomCallFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        val delegate = retrofit.nextCallAdapter(this, returnType, annotations)
        return ErrorsCallAdapter(delegateAdapter = delegate as CallAdapter<Any, Call<*>>)
    }

    class ErrorsCallAdapter(
        private val delegateAdapter: CallAdapter<Any, Call<*>>
    ) : CallAdapter<Any, Call<*>> by delegateAdapter {
        override fun adapt(call: Call<Any>): Call<*> {
            return delegateAdapter.adapt(CallWithErrorHandling(call))
        }
    }

    class CallWithErrorHandling(
        private val delegate: Call<Any>
    ) : Call<Any> by delegate, Tagged {
        override fun enqueue(callback: Callback<Any>) {
            delegate.enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    logger.d(TAG, "onResponse - response=$response")
                    if (response.isSuccessful) {
                        callback.onResponse(call, response)
                    } else {
                        callback.onFailure(call, HttpException(response))
                    }
                }

                override fun onFailure(call: Call<Any>, exception: Throwable) {
                    logger.d(TAG, "onFailure - exception=$exception")
                    callback.onFailure(call, exception)
                }
            })
        }

        override fun clone(): Call<Any> = delegate.clone()

        override fun execute(): Response<Any> = delegate.execute()
    }
}