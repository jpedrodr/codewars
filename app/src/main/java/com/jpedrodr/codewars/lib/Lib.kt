package com.jpedrodr.codewars.lib

import com.jpedrodr.codewars.lib.database.AppDatabase
import com.jpedrodr.codewars.lib.interactor.ChallengeInteractor
import com.jpedrodr.codewars.lib.interactor.OfflineModeInteractor
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStoreProvider

// This should be in a separate kotlin module since it has no Android dependencies.
// Lib shouldn't have access to the layers above (i.e domain and app)
interface Lib {

    companion object {
        // Lib should receive an interface of AppDatabase, since it shouldn't have access to android classes
        // That interface should be implemented by the app
        operator fun invoke(
            database: AppDatabase,
            keyValueStoreProvider: KeyValueStoreProvider
        ): Lib = LibCompositionRoot(database, keyValueStoreProvider)
    }

    /**
     * Interactor responsible for challenges
     */
    val challengeInteractor: ChallengeInteractor

    /**
     * Interactor responsible for offline mode
     */
    val offlineModeInteractor: OfflineModeInteractor
}