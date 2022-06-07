package com.jpedrodr.codewars.app.ui

import android.os.Bundle
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.app.ui.challenge.list.ChallengeListFragment
import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), Tagged {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initView()

//        click do item
//            fragment de detalhes
//        comunicação ao BE
    }

    private fun initView() {
        setupListFragment()
        setupToolbar()
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
}