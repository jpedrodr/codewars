package com.jpedrodr.codewars.domain.model.mapper

typealias LibCompletedChallenge = com.jpedrodr.codewars.lib.model.CompletedChallenge
typealias DomainCompletedChallenge = com.jpedrodr.codewars.domain.model.CompletedChallenge

fun List<LibCompletedChallenge>.mapToDomain(): List<DomainCompletedChallenge> {
    return map {
        DomainCompletedChallenge(it.id, it.name, it.slug, it.completedAt, it.completedLanguages)
    }
}