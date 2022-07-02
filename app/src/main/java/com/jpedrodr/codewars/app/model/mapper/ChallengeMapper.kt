package com.jpedrodr.codewars.app.model.mapper

import com.jpedrodr.codewars.app.model.CompletedChallenge

typealias DomainCompletedChallenge = com.jpedrodr.codewars.domain.model.CompletedChallenge

fun List<DomainCompletedChallenge>.mapToUi(): List<CompletedChallenge> {
    return map {
        CompletedChallenge(it.id, it.name, it.slug, it.completedAt, it.completedLanguages)
    }
}