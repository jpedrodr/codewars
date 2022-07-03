package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.lib.interactor.ChallengeInteractor

class CompletedChallengesRefreshTimestampUseCase(
    private val challengeInteractor: ChallengeInteractor
) {
    fun setTimestamp(timestamp: Long) {
        challengeInteractor.setCompletedChallengesRefreshTimestamp(timestamp)
    }

    fun getTimestamp(): Long = challengeInteractor.getCompletedChallengesRefreshTimestamp()
}