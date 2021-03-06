package com.jpedrodr.codewars.app.ui.challenge.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jpedrodr.codewars.app.ui.BaseFragment
import com.jpedrodr.codewars.app.ui.MainViewModel
import com.jpedrodr.codewars.app.ui.activityViewModel
import com.jpedrodr.codewars.app.ui.viewModel
import com.jpedrodr.codewars.databinding.FragmentChallengeListBinding

class ChallengeListFragment : BaseFragment() {

    private val viewModel by viewModel<ChallengeListViewModel>()
    private val mainViewModel by activityViewModel<MainViewModel>()
    private val adapter = ChallengeAdapter()
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

        initView()
        loadChallenges()
        setupObservers()
    }

    private fun loadChallenges() {
        viewModel.loadChallenges()
    }

    private fun setupObservers() {
        logger.d(TAG, "setupObservers")
        viewModel.completedChallenges.observe(viewLifecycleOwner) {
            logger.d(TAG, "completedChallenges=${it.size}")
            adapter.setupItems(it)
        }

        viewModel.challengesLoading.observe(viewLifecycleOwner) {
            viewBinding.challengesListSrl.isRefreshing = it
        }
    }

    private fun initView() {
        viewBinding.challengesListRv.adapter = adapter

        adapter.onItemClick = {
            logger.d(TAG, "onItemClick - challenge=$it")
            mainViewModel.openChallenge(it)
        }

        viewBinding.challengesListSrl.setOnRefreshListener {
            viewModel.refreshChallenges()
        }
    }

}