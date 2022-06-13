package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.domain.model.CompletedChallenge
import com.jpedrodr.codewars.domain.model.mapper.mapToDomain
import com.jpedrodr.codewars.lib.repository.ChallengeRepository

/**
 * Use case to get the completed challenges
 */
class GetCompletedChallengesUseCase(
    private val challengeRepository: ChallengeRepository
) : Tagged {

    suspend operator fun invoke(): List<CompletedChallenge> {
        val completedChallenges = challengeRepository.getCompletedChallenges().mapToDomain()

        logger.d(TAG, "invoke - completedChallenges=${completedChallenges.size}")
        return completedChallenges
    }

}