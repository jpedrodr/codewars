package com.jpedrodr.codewars.lib.repository

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.model.CompletedChallenge
import com.jpedrodr.codewars.lib.model.CompletedChallengesResponse
import com.jpedrodr.codewars.lib.model.dto.CompletedChallengesResponseDto
import com.jpedrodr.codewars.lib.model.mapper.mapToLib
import com.jpedrodr.codewars.lib.network.*
import kotlinx.coroutines.*

private const val DEFAULT_PAGE_INDEX = 0
private const val DEFAUlT_NO_DATA_SIZE = 0

/**
 * Repository responsible for communicating with the data sources regarding the challenges
 */
class ChallengeRepository(private val challengeApi: ChallengeApi) : Tagged {

    /**
     * Gets the completed challenges from the backend
     */
    suspend fun getCompletedChallenges(): List<CompletedChallenge> = withContext(Dispatchers.IO) {
        val firstPageResponse = requestCompletedChallenges(DEFAULT_PAGE_INDEX)

        if (firstPageResponse.totalItems == 0) {
            logger.d(TAG, "getCompletedChallenges got no items")
            return@withContext emptyList()
        }

        logger.d(
            TAG,
            "getCompletedChallenges - totalPages=${firstPageResponse.totalPages}, totalItems=${firstPageResponse.totalItems}"
        )

        if (firstPageResponse.totalPages == DEFAUlT_NO_DATA_SIZE || firstPageResponse.totalItems == DEFAUlT_NO_DATA_SIZE) {
            // there are no challenges for the requested used
            return@withContext emptyList()
        }

        val totalPages = firstPageResponse.totalPages

        if (totalPages == 1) { // if there is only 1 page (and we already have on the first request), return it immediately
            return@withContext firstPageResponse.data
        }

        val deferredCalls = mutableListOf<Deferred<CompletedChallengesResponse>>()

        for (page in 1..totalPages) { // start on page = 1 because we already have the first page
            val request = async {
                requestCompletedChallenges(page)
            }

            deferredCalls.add(request)
        }

        return@withContext firstPageResponse.data + deferredCalls.awaitAll().flatMap { it.data }
    }

    private suspend fun requestCompletedChallenges(page: Int): CompletedChallengesResponse {
        val response = doCompletedChallenges(page)

        if (response is Error) {
            logger.d(TAG, "doCompletedChallenges got an error - error=$response")
            return CompletedChallengesResponse(0, 0, emptyList())
        }

        return response.unwrapSuccess().mapToLib()
    }

    private suspend fun doCompletedChallenges(page: Int): Result<CompletedChallengesResponseDto> {
        val result = performNetwork(page) {
            challengeApi.getCompletedChallenges(it)
        }

        return result
    }
}