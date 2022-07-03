package com.jpedrodr.codewars.lib.repository

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.database.dao.CompletedChallengeDao
import com.jpedrodr.codewars.lib.database.model.mapper.mapToEntity
import com.jpedrodr.codewars.lib.database.model.mapper.mapToLib
import com.jpedrodr.codewars.lib.model.CompletedChallenge
import com.jpedrodr.codewars.lib.model.CompletedChallengesResponse
import com.jpedrodr.codewars.lib.model.mapper.mapToLib
import com.jpedrodr.codewars.lib.network.ChallengeApi
import com.jpedrodr.codewars.lib.network.Error
import com.jpedrodr.codewars.lib.network.performNetwork
import com.jpedrodr.codewars.lib.network.unwrapSuccess
import kotlinx.coroutines.*

private const val DEFAULT_PAGE_INDEX = 0
private const val DEFAUlT_NO_DATA_SIZE = 0

/**
 * Repository responsible for communicating with the data sources regarding the challenges
 */
class ChallengeRepository(
    private val challengeApi: ChallengeApi,
    private val completedChallengeDao: CompletedChallengeDao,
    private val offlineModeRepository: OfflineModeRepository
) : Tagged {

    /**
     * Gets the completed challenges from the backend
     */
    suspend fun getCompletedChallenges(): List<CompletedChallenge> = withContext(Dispatchers.IO) {
        logger.d(TAG, "getCompletedChallenges - isOffline=${offlineModeRepository.isOffline()}")
        if (offlineModeRepository.isOffline()) {
            logger.d(
                TAG,
                "getCompletedChallenges - in offline mode, getting challenges from database"
            )
            return@withContext getChallengesFromDatabase()
        }

        if (checkDataValidity()) {
            logger.d(
                TAG,
                "getCompletedChallenges - data is valid, getting challenges from database"
            )
            return@withContext getChallengesFromDatabase()
        }

        val completedChallenges = getCompletedChallengesFromNetwork()

        launch {
            insertChallengesInDatabase(completedChallenges)
        }

        return@withContext completedChallenges
    }

    private fun checkDataValidity(): Boolean {
        return true
    }

    private fun getChallengesFromDatabase(): List<CompletedChallenge> {
        if (!completedChallengeDao.hasData()) {
            logger.d(TAG, "getChallengesFromDatabase - database has no data")
            return emptyList()
        }

        val challenges = completedChallengeDao.getAll().mapToLib()

        logger.d(
            TAG,
            "getChallengesFromDatabase - database has challenges, challenges=${challenges.size}"
        )

        return challenges
    }

    private fun insertChallengesInDatabase(challenges: List<CompletedChallenge>) {
        logger.d(TAG, "insertChallengesInDatabase - challenges=${challenges.size}")
        completedChallengeDao.insertAll(challenges.mapToEntity())
    }

    private suspend fun getCompletedChallengesFromNetwork(): List<CompletedChallenge> =
        coroutineScope {
            val firstPageResponse = requestCompletedChallengesFromNetwork(DEFAULT_PAGE_INDEX)

            if (firstPageResponse.totalItems == 0) {
                logger.d(TAG, "getCompletedChallenges got no items")
                return@coroutineScope emptyList()
            }

            logger.d(
                TAG,
                "getCompletedChallenges - totalPages=${firstPageResponse.totalPages}, totalItems=${firstPageResponse.totalItems}"
            )

        if (firstPageResponse.totalPages == DEFAUlT_NO_DATA_SIZE || firstPageResponse.totalItems == DEFAUlT_NO_DATA_SIZE) {
            // there are no challenges for the requested used
            return@coroutineScope emptyList()
        }

        val totalPages = firstPageResponse.totalPages

        if (totalPages == 1) { // if there is only 1 page (and we already have on the first request), return it immediately
            return@coroutineScope firstPageResponse.data
        }

        val deferredCalls = mutableListOf<Deferred<CompletedChallengesResponse>>()

        for (page in 1..totalPages) { // start on page = 1 because we already have the first page
            val request = async {
                requestCompletedChallengesFromNetwork(page)
            }

            deferredCalls.add(request)
        }

            return@coroutineScope firstPageResponse.data + deferredCalls.awaitAll()
                .flatMap { it.data }
    }

    private suspend fun requestCompletedChallengesFromNetwork(page: Int): CompletedChallengesResponse {
        val result = performNetwork(page) {
            challengeApi.getCompletedChallenges(it)
        }

        if (result is Error) {
            logger.d(TAG, "doCompletedChallenges got an error - error=$result")
            return CompletedChallengesResponse(0, 0, emptyList())
        }

        return result.unwrapSuccess().mapToLib()
    }
}