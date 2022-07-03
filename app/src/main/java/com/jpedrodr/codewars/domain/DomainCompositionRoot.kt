package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.domain.usecase.CompletedChallengesRefreshTimestampUseCase
import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import com.jpedrodr.codewars.domain.usecase.SetOfflineModeUseCase
import com.jpedrodr.codewars.lib.Lib
import com.jpedrodr.codewars.lib.database.AppDatabase

internal class DomainCompositionRoot(database: AppDatabase) : Domain {

    private val lib = Lib(database)

    override val getCompletedChallengesUseCase: GetCompletedChallengesUseCase
        get() = GetCompletedChallengesUseCase(lib.challengeInteractor)

    override val setOfflineModeUseCase: SetOfflineModeUseCase
        get() = SetOfflineModeUseCase(lib.offlineModeInteractor)

    override val completedChallengesRefreshTimestampUseCase: CompletedChallengesRefreshTimestampUseCase
        get() = CompletedChallengesRefreshTimestampUseCase(lib.challengeInteractor)
}