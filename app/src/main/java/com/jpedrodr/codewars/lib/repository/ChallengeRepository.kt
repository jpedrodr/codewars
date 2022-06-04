package com.jpedrodr.codewars.lib.repository

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.model.CompletedChallenge

/**
 * Repository responsible for communicating with the data sources regarding the challenges
 */
class ChallengeRepository : Tagged {

    /**
     * Gets the completed challenges from the backend
     */
    fun getCompletedChallenges(): List<CompletedChallenge> {
        return emptyList()
    }
}