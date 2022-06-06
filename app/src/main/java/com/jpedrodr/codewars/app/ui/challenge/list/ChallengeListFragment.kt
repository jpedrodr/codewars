package com.jpedrodr.codewars.app.ui.challenge.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jpedrodr.codewars.app.ui.BaseFragment
import com.jpedrodr.codewars.app.ui.viewModel
import com.jpedrodr.codewars.databinding.FragmentChallengeListBinding

class ChallengeListFragment : BaseFragment() {

    private val viewModel by viewModel<ChallengeListViewModel>()
    private val adapter = ChallangeAdapter()
    private lateinit var viewBinding: FragmentChallengeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentChallengeListBinding.inflate(inflater, container, false)

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
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

    private fun setupAdapter() {
        viewBinding.challengesListRv.adapter = adapter
    }

}