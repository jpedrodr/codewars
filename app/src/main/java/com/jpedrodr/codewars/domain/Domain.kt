package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.app.network.ConnectivityManager
import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase

interface Domain {

    companion object {
        operator fun invoke(
            connectivityManager: ConnectivityManager
        ): Domain = DomainCompositionRoot(connectivityManager)
    }

    val getCompletedChallengesUseCase: GetCompletedChallengesUseCase

}