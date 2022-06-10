package com.jpedrodr.codewars.app.ui

import android.os.Bundle
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.app.ui.challenge.bundle.CompletedChallengeBundle
import com.jpedrodr.codewars.app.ui.challenge.details.ChallengeDetailsFragment
import com.jpedrodr.codewars.app.ui.challenge.list.ChallengeListFragment
import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.databinding.ActivityMainBinding
import com.jpedrodr.codewars.domain.model.CompletedChallenge

class MainActivity : BaseActivity(), Tagged {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initView()

//        comunicação ao BE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onBack()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBack()
        return super.onSupportNavigateUp()
    }

    private fun initView() {
        setupListFragment()
        setupToolbar()
        setupObservers()
    }

    private fun onBack() {
        logger.d(TAG, "onBack called")
        supportFragmentManager.popBackStack()
        setupListToolbar()
    }


    private fun setupToolbar() {
        setSupportActionBar(binding.mainToolbar)
        setupListToolbar()
    }

    private fun setupListFragment() {
        logger.d(TAG, "setupListFragment called")
        supportFragmentManager.beginTransaction().run {
            replace(R.id.fragment_container, ChallengeListFragment())
            commit()
        }
    }

    private fun setupObservers() {
        viewModel.openChallenge.observe(this) { challenge ->
            openChallenge(challenge)
        }
    }

    private fun openChallenge(challenge: CompletedChallenge) {
        logger.d(TAG, "openChallenge - challenge=$challenge")
        supportFragmentManager.beginTransaction().run {
            add(
                R.id.fragment_container,
                ChallengeDetailsFragment().also {
                    it.arguments = CompletedChallengeBundle.create(challenge)
                }
            )
            addToBackStack(null)
            commit()
        }

        setDetailsToolbar(challenge)
    }

    private fun setupListToolbar() {
        supportActionBar?.apply {
            title = getString(R.string.completed_challenges)
            setDisplayShowHomeEnabled(false)
            setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun setDetailsToolbar(challenge: CompletedChallenge) {
        supportActionBar?.apply {
            title = getString(R.string.challenge, challenge.name)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

}