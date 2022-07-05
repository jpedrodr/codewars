package com.jpedrodr.codewars.app.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.app.model.CompletedChallenge
import com.jpedrodr.codewars.app.ui.challenge.bundle.CompletedChallengeBundle
import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), Tagged {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        initView()
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
        setupToolbar()
        setupObservers()
    }

    private fun onBack() {
        logger.d(TAG, "onBack called")
        navController.navigateUp()
        setupListToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.mainToolbar)
        setupListToolbar()
    }

    private fun setupObservers() {
        viewModel.openChallenge.observe(this) { challenge ->
            openChallenge(challenge)
        }
    }

    private fun openChallenge(challenge: CompletedChallenge) {
        logger.d(TAG, "openChallenge - challenge=$challenge")
        navController.navigate(
            R.id.action_challengeListFragment_to_challengeDetailsFragment,
            CompletedChallengeBundle.create(challenge)
        )
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
            title = challenge.name
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

}