package com.jpedrodr.codewars.lib

import com.jpedrodr.codewars.lib.database.AppDatabase
import com.jpedrodr.codewars.lib.interactor.ChallengeInteractor
import com.jpedrodr.codewars.lib.interactor.OfflineModeInteractor

// This should be in a separate kotlin module since it has no Android dependencies.
// Lib shouldn't have access to the layers above (i.e domain and app)
interface Lib {

    companion object {
        // database shouldn't be passed from app since lib shouldn't have access to app
        operator fun invoke(database: AppDatabase): Lib = LibCompositionRoot(database)
    }


    val challengeInteractor: ChallengeInteractor

    val offlineModeInteractor: OfflineModeInteractor
}