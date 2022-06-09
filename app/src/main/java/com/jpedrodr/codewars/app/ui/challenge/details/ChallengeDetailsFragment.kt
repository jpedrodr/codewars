package com.jpedrodr.codewars.app.ui.challenge.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jpedrodr.codewars.app.ui.BaseFragment
import com.jpedrodr.codewars.app.ui.challenge.bundle.CompletedChallengeBundle
import com.jpedrodr.codewars.databinding.FragmentChallengeDetailsBinding
import com.jpedrodr.codewars.domain.model.CompletedChallenge

class ChallengeDetailsFragment : BaseFragment() {

    lateinit var viewBinding: FragmentChallengeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentChallengeDetailsBinding.inflate(layoutInflater, container, false)

        CompletedChallengeBundle.fromBundle(savedInstanceState)?.also {
            setupData(it)
        }

        return viewBinding.root
    }


    private fun setupData(challenge: CompletedChallenge) {
        logger.d(TAG, "setupData - challenge=$challenge")
        // put data in view
    }

}