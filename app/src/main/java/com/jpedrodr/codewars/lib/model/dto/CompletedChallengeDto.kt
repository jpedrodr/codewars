package com.jpedrodr.codewars.lib.model.dto

/**
 * DTO Class for a completed challenge
 */
class CompletedChallengeDto(
    val id: String = "",
    val name: String = "",
    val slug: String = "",
    val completedAt: String = "",
    val completedLanguages: List<String> = emptyList()
)