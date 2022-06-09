package com.jpedrodr.codewars.app.ui

import android.os.Bundle
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.app.ui.challenge.bundle.CompletedChallengeBundle
import com.jpedrodr.codewars.app.ui.challenge.details.ChallengeDetailsFragment
import com.jpedrodr.codewars.app.ui.challenge.list.ChallengeListFragment
import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), Tagged {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initView()

//            fragment de detalhes
//        comunicação ao BE
    }

    private fun initView() {
        setupListFragment()
        setupToolbar()
        setupObservers()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.mainToolbar)
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
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }
}