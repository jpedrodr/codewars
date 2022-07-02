package com.jpedrodr.codewars.app.ui.challenge.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jpedrodr.codewars.app.model.CompletedChallenge
import com.jpedrodr.codewars.app.ui.BaseFragment
import com.jpedrodr.codewars.app.ui.challenge.ChallengeDateFormatter
import com.jpedrodr.codewars.app.ui.challenge.bundle.CompletedChallengeBundle
import com.jpedrodr.codewars.databinding.FragmentChallengeDetailsBinding

class ChallengeDetailsFragment : BaseFragment() {

    private lateinit var viewBinding: FragmentChallengeDetailsBinding
    private val formatter = ChallengeDateFormatter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentChallengeDetailsBinding.inflate(layoutInflater, container, false)

        CompletedChallengeBundle.fromBundle(arguments)?.also {
            setupData(it)
        }

        return viewBinding.root
    }


    private fun setupData(challenge: CompletedChallenge) = with(viewBinding) {
        logger.d(TAG, "setupData - challenge=$challenge")
        challengeDetailsNameTv.text = challenge.name
        challengeDetailsSlugTv.text = challenge.slug
        challengeDetailsCompletedAtTv.text = formatter.format(challenge.completedAt)
        challengeDetailsCompletedLanguageTv.text = challenge.completedLanguages.joinToString()
    }

}