package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase

internal class DomainCompositionRoot : Domain {

    override val getCompletedChallengesUseCase: GetCompletedChallengesUseCase
        get() = GetCompletedChallengesUseCase()
}