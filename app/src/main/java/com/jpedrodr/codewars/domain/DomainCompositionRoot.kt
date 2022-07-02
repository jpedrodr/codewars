package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.app.network.ConnectivityManager
import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import com.jpedrodr.codewars.lib.Lib

internal class DomainCompositionRoot(
    private val connectivityManager: ConnectivityManager
) : Domain {

    private val lib = Lib()

    override val getCompletedChallengesUseCase: GetCompletedChallengesUseCase
        get() = GetCompletedChallengesUseCase(lib.challengeRepository)
}