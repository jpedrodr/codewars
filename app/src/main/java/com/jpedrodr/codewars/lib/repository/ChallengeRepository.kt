package com.jpedrodr.codewars.lib.repository

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.model.CompletedChallenge
import com.jpedrodr.codewars.lib.model.CompletedChallengesResponse
import com.jpedrodr.codewars.lib.network.ChallengeApi
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

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
        val firstPageResult = requestCompletedChallenges(DEFAULT_PAGE_INDEX)

        logger.d(
            TAG,
            "getCompletedChallenges - totalPages=${firstPageResult.totalPages}, totalItems=${firstPageResult.totalItems}"
        )

        if (firstPageResult.totalPages == DEFAUlT_NO_DATA_SIZE || firstPageResult.totalItems == DEFAUlT_NO_DATA_SIZE) {
            // there are no challenges for the requested used
            return@withContext emptyList()
        }

        val totalPages = firstPageResult.totalPages

        if (totalPages == 1) { // if there is only 1 page (and we already have on the first request), return it immediately
            return@withContext firstPageResult.data
        }

        val deferredCalls = mutableListOf<Deferred<CompletedChallengesResponse>>()

        for (page in 1..totalPages) { // start on page = 1 because we already have the first page
            val request = async {
                requestCompletedChallenges(page)
            }

            deferredCalls.add(request)
        }

        return@withContext firstPageResult.data + deferredCalls.awaitAll().flatMap { it.data }
    }

    private suspend fun requestCompletedChallenges(page: Int): CompletedChallengesResponse {
        return challengeApi.getCompletedChallenges(page).body()
            ?: return CompletedChallengesResponse(0, 0, emptyList())
    }
}