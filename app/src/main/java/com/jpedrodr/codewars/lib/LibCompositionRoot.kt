package com.jpedrodr.codewars.lib

import com.jpedrodr.codewars.lib.repository.ChallengeRepository

internal class LibCompositionRoot : Lib {

    override val challengeRepository: ChallengeRepository
        get() = ChallengeRepository()
}