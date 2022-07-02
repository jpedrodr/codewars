package com.jpedrodr.codewars.lib.model.mapper

import com.jpedrodr.codewars.lib.model.CompletedChallenge
import com.jpedrodr.codewars.lib.model.CompletedChallengesResponse
import com.jpedrodr.codewars.lib.model.dto.CompletedChallengeDto
import com.jpedrodr.codewars.lib.model.dto.CompletedChallengesResponseDto


fun CompletedChallengesResponseDto.mapToLib(): CompletedChallengesResponse {
    return CompletedChallengesResponse(totalPages, totalItems, data.mapToLib())
}

fun List<CompletedChallengeDto>.mapToLib(): List<CompletedChallenge> {
    return map {
        CompletedChallenge(it.id, it.name, it.slug, it.completedAt, it.completedLanguages)
    }
}