package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import com.jpedrodr.codewars.domain.usecase.SetOfflineModeUseCase
import com.jpedrodr.codewars.lib.Lib

internal class DomainCompositionRoot : Domain {

    private val lib = Lib()

    override val getCompletedChallengesUseCase: GetCompletedChallengesUseCase
        get() = GetCompletedChallengesUseCase(lib.challengeRepository)

    override val setOfflineModeUseCase: SetOfflineModeUseCase
        get() = SetOfflineModeUseCase(lib.offlineModeRepository)
}