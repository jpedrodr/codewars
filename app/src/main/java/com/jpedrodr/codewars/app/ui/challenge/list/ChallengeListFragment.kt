package com.jpedrodr.codewars.app.ui.challenge.list

import android.os.Bundle
import android.view.View
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.app.ui.BaseFragment
import com.jpedrodr.codewars.app.ui.viewModel

class ChallengeListFragment : BaseFragment(R.layout.fragment_challenge_list) {

    private val viewModel by viewModel<ChallengeListViewModel>()
    private val adapter = ChallangeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadChallenges()
        setupObservers()
    }

    private fun loadChallenges() {
        viewModel.initialize()
    }

    private fun setupObservers() {
        logger.d(TAG, "setupObservers")
        viewModel.completedChallenges.observe(viewLifecycleOwner) {
            logger.d(TAG, "completedChallenges=${it.size}")
            adapter.setupItems(it)
        }
    }

}