package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.domain.model.CompletedChallenge
import com.jpedrodr.codewars.domain.model.mapper.mapToDomain
import com.jpedrodr.codewars.lib.interactor.ChallengeInteractor

/**
 * Use case to get the completed challenges
 */
class GetCompletedChallengesUseCase(
    private val challengeInteractor: ChallengeInteractor
) : Tagged {

    suspend operator fun invoke(forceUpdate: Boolean = false): List<CompletedChallenge> {
        val completedChallenges =
            challengeInteractor.getCompletedChallenges(forceUpdate).mapToDomain()

        logger.d(TAG, "invoke - completedChallenges=${completedChallenges.size}")
        return completedChallenges
    }

}