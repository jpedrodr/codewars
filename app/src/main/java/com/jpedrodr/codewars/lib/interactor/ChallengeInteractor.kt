package com.jpedrodr.codewars.lib.interactor

import com.jpedrodr.codewars.lib.model.CompletedChallenge
import com.jpedrodr.codewars.lib.repository.ChallengeRepository

class ChallengeInteractor(
    private val challengeRepository: ChallengeRepository
) {

    suspend fun getCompletedChallenges(): List<CompletedChallenge> {
        return challengeRepository.getCompletedChallenges()
    }

    fun setCompletedChallengesRefreshTimestamp(timestamp: Long) {
        challengeRepository.setCompletedChallengesRefreshTimestamp(timestamp)
    }

    fun getCompletedChallengesRefreshTimestamp(): Long {
        return challengeRepository.getCompletedChallengesRefreshTimestamp()
    }
}