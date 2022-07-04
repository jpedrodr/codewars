package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import com.jpedrodr.codewars.domain.usecase.OfflineModeChangesUseCase
import com.jpedrodr.codewars.domain.usecase.SetOfflineModeUseCase
import com.jpedrodr.codewars.lib.Lib
import com.jpedrodr.codewars.lib.database.AppDatabase
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStoreProvider

internal class DomainCompositionRoot(
    database: AppDatabase,
    keyValueStoreProvider: KeyValueStoreProvider
) : Domain {

    private val lib = Lib(database, keyValueStoreProvider)

    override val getCompletedChallengesUseCase: GetCompletedChallengesUseCase
        get() = GetCompletedChallengesUseCase(lib.challengeInteractor)

    override val setOfflineModeUseCase: SetOfflineModeUseCase
        get() = SetOfflineModeUseCase(lib.offlineModeInteractor)

    override val offlineModeChangesUseCase: OfflineModeChangesUseCase
        get() = OfflineModeChangesUseCase(lib.offlineModeInteractor)
}