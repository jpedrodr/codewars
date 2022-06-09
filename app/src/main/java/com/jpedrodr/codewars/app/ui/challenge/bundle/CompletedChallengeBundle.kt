package com.jpedrodr.codewars.app.ui.challenge.bundle

import android.os.Bundle
import com.jpedrodr.codewars.domain.model.CompletedChallenge

private const val CHALLENGE_KEY = "CHALLENGE"

class CompletedChallengeBundle {

    companion object {
        fun create(challenge: CompletedChallenge): Bundle {
            return Bundle().apply {
                putSerializable(CHALLENGE_KEY, challenge)
            }
        }

        fun fromBundle(bundle: Bundle?): CompletedChallenge? {
            return bundle?.getSerializable(CHALLENGE_KEY) as? CompletedChallenge
        }
    }
}