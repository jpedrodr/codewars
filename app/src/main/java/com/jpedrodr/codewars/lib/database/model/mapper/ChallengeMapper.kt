package com.jpedrodr.codewars.lib.database.model.mapper

import com.jpedrodr.codewars.lib.database.model.CompletedChallengeEntity
import com.jpedrodr.codewars.lib.model.CompletedChallenge

fun List<CompletedChallengeEntity>.mapToLib(): List<CompletedChallenge> {
    return map {
        CompletedChallenge(it.id, it.name, it.slug, it.completedAt, it.completedLanguages)
    }
}

fun List<CompletedChallenge>.mapToEntity(): List<CompletedChallengeEntity> {
    return map {
        CompletedChallengeEntity(it.id, it.name, it.slug, it.completedAt, it.completedLanguages)
    }
}