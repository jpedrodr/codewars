package com.jpedrodr.codewars.lib.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ChallengeApi {

    @GET("/code-challenges/completed")
    fun getCompletedChallenges(@Query("page") page: Int)
}