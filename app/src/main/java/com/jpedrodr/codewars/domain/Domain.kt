package com.jpedrodr.codewars.domain

import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import com.jpedrodr.codewars.domain.usecase.OfflineModeChangesUseCase
import com.jpedrodr.codewars.domain.usecase.SetOfflineModeUseCase
import com.jpedrodr.codewars.lib.database.AppDatabase
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStoreProvider

interface Domain {

    companion object {
        operator fun invoke(
            database: AppDatabase,
            keyValueStoreProvider: KeyValueStoreProvider
        ): Domain = DomainCompositionRoot(database, keyValueStoreProvider)
    }

    val getCompletedChallengesUseCase: GetCompletedChallengesUseCase

    val setOfflineModeUseCase: SetOfflineModeUseCase

    val offlineModeChangesUseCase: OfflineModeChangesUseCase

}