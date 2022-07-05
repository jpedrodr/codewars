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

    /**
     * Use case responsible for getting the completed challenges
     */
    val getCompletedChallengesUseCase: GetCompletedChallengesUseCase

    /**
     * Use case responsible for setting the offline mode state
     */
    val setOfflineModeUseCase: SetOfflineModeUseCase

    /**
     * Use case responsible for providing offline mode states
     */
    val offlineModeChangesUseCase: OfflineModeChangesUseCase

}