package com.jpedrodr.codewars.lib

import com.jpedrodr.codewars.lib.repository.ChallengeRepository
import com.jpedrodr.codewars.lib.repository.OfflineModeRepository

// This should be in a separate kotlin module since it has no Android dependencies.
// Lib shouldn't have access to the layers above (i.e domain and app)
interface Lib {

    companion object {
        operator fun invoke(): Lib = LibCompositionRoot()
    }

    val challengeRepository: ChallengeRepository

    val offlineModeRepository: OfflineModeRepository
}