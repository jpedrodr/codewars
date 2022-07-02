package com.jpedrodr.codewars.lib.model

/**
 * Class to hold data of a completed challenge
 */
class CompletedChallenge(
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: String,
    val completedLanguages: List<String>
)