package com.jpedrodr.codewars.lib.repository

import com.jpedrodr.codewars.commons.INVALID_TIMESTAMP
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
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStoreProvider
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStoreValues
import java.time.Duration
import java.time.Instant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val DEFAULT_PAGE_INDEX = 0
private const val DEFAUlT_NO_DATA_SIZE = 0
private val COMPLETED_CHALLENGES_CACHE_VALIDITY =
    Duration.ofHours(1) // minimum duration between automatic refreshes

private const val COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY =
    "COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY"

/**
 * Repository responsible for communicating with the data sources regarding the challenges
 */
class ChallengeRepository(
    private val challengeApi: ChallengeApi,
    private val completedChallengeDao: CompletedChallengeDao,
    private val offlineModeRepository: OfflineModeRepository,
    private val keyValueStoreProvider: KeyValueStoreProvider,
    private val challengesScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : Tagged {

    private val keyValueStore by lazy { keyValueStoreProvider.store(KeyValueStoreValues.KEY_CHALLENGES) }

    /**
     * Gets the completed challenges from the backend
     */
    suspend fun getCompletedChallenges(forceUpdate: Boolean = false): List<CompletedChallenge> =
        withContext(Dispatchers.IO) {
            if (offlineModeRepository.isOffline()) {
                logger.d(
                    TAG,
                    "getCompletedChallenges - in offline mode, getting challenges from database"
                )
                return@withContext getChallengesFromDatabase()
            }

            if (!forceUpdate && checkDataValidity()) {
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

    fun refreshDataIfNeeded() = challengesScope.launch {
        if (checkDataValidity()) {
            logger.d(TAG, "refreshDataIfNeeded - data is valid, no need to refresh")
            return@launch
        }

        val completedChallenges = getCompletedChallengesFromNetwork()
        insertChallengesInDatabase(completedChallenges)
    }

    private suspend fun checkDataValidity(): Boolean {
        val timestamp = getCompletedChallengesLastRefreshTimestamp()
        if (timestamp == INVALID_TIMESTAMP) {
            logger.d(TAG, "checkDataValidity - no last refresh timestamp, returning false")
            return false
        }

        val now = Instant.now().toEpochMilli()
        // if the cache validity is longer than the current time minis the last refresh timestamp, it is valid
        val isValid = COMPLETED_CHALLENGES_CACHE_VALIDITY.toMillis() > now - timestamp
        logger.d(
            TAG,
            "checkDataValidity - isValid=$isValid, timestamp=$timestamp, now=$now"
        )
        return isValid
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

    private suspend fun insertChallengesInDatabase(challenges: List<CompletedChallenge>) {
        completedChallengeDao.insertAll(challenges.mapToEntity())
        val timestamp = Instant.now().toEpochMilli()
        setCompletedChallengesLastRefreshTimestamp(timestamp)
        logger.d(
            TAG,
            "insertChallengesInDatabase - challenges=${challenges.size}, timestamp=$timestamp"
        )
    }

    private suspend fun getCompletedChallengesFromNetwork(): List<CompletedChallenge> =
        with(challengesScope) {
            val firstPageResponse = requestCompletedChallengesFromNetwork(DEFAULT_PAGE_INDEX)

            if (firstPageResponse.totalItems == 0) {
                logger.d(TAG, "getCompletedChallenges got no items")
                return@with emptyList()
            }

            logger.d(
                TAG,
                "getCompletedChallenges - totalPages=${firstPageResponse.totalPages}, totalItems=${firstPageResponse.totalItems}"
            )

            if (firstPageResponse.totalPages == DEFAUlT_NO_DATA_SIZE || firstPageResponse.totalItems == DEFAUlT_NO_DATA_SIZE) {
                // there are no challenges for the requested used
                return@with emptyList()
            }

            val totalPages = firstPageResponse.totalPages

            if (totalPages == 1) { // if there is only 1 page (and we already have on the first request), return it immediately
                return@with firstPageResponse.data
            }

            val deferredCalls = mutableListOf<Deferred<CompletedChallengesResponse>>()

            for (page in 1..totalPages) { // start on page = 1 because we already have the first page
                val request = async {
                    requestCompletedChallengesFromNetwork(page)
                }

                deferredCalls.add(request)
            }

            return@with firstPageResponse.data + deferredCalls.awaitAll()
                .flatMap { it.data }.distinctBy { it.id }
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

    private suspend fun setCompletedChallengesLastRefreshTimestamp(timestamp: Long) {
        logger.d(TAG, "setCompletedChallengesLastRefreshTimestamp - timestamp=$timestamp")
        keyValueStore.write(
            COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY,
            timestamp,
            Long::class.java
        )
    }

    private suspend fun getCompletedChallengesLastRefreshTimestamp(): Long {
        val timestamp =
            keyValueStore.read(
                COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY,
                INVALID_TIMESTAMP,
                Long::class.java
            )
        logger.d(TAG, "getCompletedChallengesLastRefreshTimestamp - timestamp=$timestamp")
        return timestamp
    }
}