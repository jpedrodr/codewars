package com.jpedrodr.codewars.lib.model

import java.time.Instant

/**
 * Class to hold data of a completed challenge
 */
class CompletedChallenge(
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: Instant
)