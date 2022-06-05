package com.jpedrodr.codewars.app.ui.challenge.list

import android.os.Bundle
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.app.ui.BaseFragment
import com.jpedrodr.codewars.app.ui.viewModel

class ChallengeListFragment : BaseFragment(R.layout.fragment_challenge_list) {

    val viewModel by viewModel<ChallengeListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadChallenges()
    }

    private fun loadChallenges() {
//        viewModel.initialize()
    }

}