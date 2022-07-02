package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import com.jpedrodr.codewars.domain.usecase.SetOfflineModeUseCase

interface Domain {

    companion object {
        operator fun invoke(): Domain = DomainCompositionRoot()
    }

    val getCompletedChallengesUseCase: GetCompletedChallengesUseCase

    val setOfflineModeUseCase: SetOfflineModeUseCase

}