package com.jpedrodr.codewars.lib.model.dto

/**
 * DTO class to parse the GetCompletedChallenges response
 */
class CompletedChallengesResponseDto(
    val totalPages: Int,
    val totalItems: Int,
    val data: List<CompletedChallengeDto>
)