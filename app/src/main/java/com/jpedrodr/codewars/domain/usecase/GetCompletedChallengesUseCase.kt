package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.domain.model.CompletedChallenge
import com.jpedrodr.codewars.lib.repository.ChallengeRepository
import java.time.Instant

/**
 * Use case to get the completed challenges
 */
class GetCompletedChallengesUseCase(
    private val challengeRepository: ChallengeRepository
) {

    operator fun invoke(): List<CompletedChallenge> {
//        challengeRepository.getCompletedChallenges()
        return createDummyData(10000)
    }

    private fun createDummyData(numberOfChallenges: Int): List<CompletedChallenge> {
        return (0 until numberOfChallenges).map {
            CompletedChallenge(
                id = "challenge_id_$it",
                name = "challenge_name_$it",
                slug = "challenge_slub_$it",
                completedAt = Instant.now(),
                listOf(
                    "Language $it",
                    "Language ${it * 2}",
                    "Language ${it * 3}",
                    "Language ${it * 4}"
                )
            )
        }
    }
}