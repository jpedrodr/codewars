package com.jpedrodr.codewars.lib.model.dto

import java.time.Instant

/**
 * DTO Class for a completed challenge
 */
class CompletedChallengeDto(
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: Instant,
    val completedLanguages: List<String>
)