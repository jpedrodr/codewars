package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import com.jpedrodr.codewars.domain.usecase.SetOfflineModeUseCase
import com.jpedrodr.codewars.lib.database.AppDatabase

interface Domain {

    companion object {
        operator fun invoke(database: AppDatabase): Domain = DomainCompositionRoot(database)
    }

    val getCompletedChallengesUseCase: GetCompletedChallengesUseCase

    val setOfflineModeUseCase: SetOfflineModeUseCase

}