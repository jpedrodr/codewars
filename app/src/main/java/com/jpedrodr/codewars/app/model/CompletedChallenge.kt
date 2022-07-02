package com.jpedrodr.codewars.app.model

import java.io.Serializable

/**
 * Class to hold data of a completed challenge
 */
data class CompletedChallenge(
    val id: String,
    val name: String,
    val slug: String,
    val completedAt: String,
    val completedLanguages: List<String>
) : Serializable {

    override fun toString(): String {
        return "CompletedChallenge(id=$id, name=$name, slug=$slug, completedAt=$completedAt, completedLanguages=$completedLanguages)"
    }
}