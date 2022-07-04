package com.jpedrodr.codewars.lib.network

import com.jpedrodr.codewars.lib.model.dto.CompletedChallengesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Challenge API related to https://dev.codewars.com/
 */
interface ChallengeApi {

    /**
     * Get completed challenge according to the given page
     * https://dev.codewars.com/#list-completed-challenges
     */
    @GET("completed")
    suspend fun getCompletedChallenges(@Query("page") page: Int): Response<CompletedChallengesResponseDto>
}