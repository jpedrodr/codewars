package com.jpedrodr.codewars.domain.model

/**
 * Class to hold data of a completed challenge
 */
class CompletedChallenge(
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: String,
    val completedLanguages: List<String>
) {

    override fun toString(): String {
        return "CompletedChallenge(id=$id, name=$name, slug=$slug, completedAt=$completedAt, completedLanguages=$completedLanguages)"
    }
}