package com.jpedrodr.codewars.lib.model

/**
 * DTO class to parse the GetCompletedChallenges response
 */
class CompletedChallengesResponse(
    val totalPages: Int,
    val totalItems: Int,
    val data: List<CompletedChallenge>
)