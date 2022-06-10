package com.jpedrodr.codewars.lib.repository

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.model.CompletedChallenge
import com.jpedrodr.codewars.lib.network.ChallengeApi

/**
 * Repository responsible for communicating with the data sources regarding the challenges
 */
class ChallengeRepository(private val challengeApi: ChallengeApi) : Tagged {

    /**
     * Gets the completed challenges from the backend
     */
    fun getCompletedChallenges(): List<CompletedChallenge> {
        val page = 1

        challengeApi.getCompletedChallenges(page)
        return emptyList()
    }
}