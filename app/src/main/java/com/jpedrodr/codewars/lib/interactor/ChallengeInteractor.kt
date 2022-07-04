package com.jpedrodr.codewars.lib.interactor

import com.jpedrodr.codewars.lib.model.CompletedChallenge
import com.jpedrodr.codewars.lib.repository.ChallengeRepository

class ChallengeInteractor(
    private val challengeRepository: ChallengeRepository
) {

    suspend fun getCompletedChallenges(forceUpdate: Boolean = false): List<CompletedChallenge> {
        return challengeRepository.getCompletedChallenges(forceUpdate)
    }
}