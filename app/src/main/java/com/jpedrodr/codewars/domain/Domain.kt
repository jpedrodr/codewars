package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase

interface Domain {

    companion object {
        operator fun invoke(): Domain = DomainCompositionRoot()
    }

    val getCompletedChallengesUseCase: GetCompletedChallengesUseCase

}