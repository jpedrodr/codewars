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

        setupFragment()

//        titulo da lista
//        click do item
//            fragment de detalhes
//        comunicação ao BE
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragment_container, ChallengeListFragment())
            commit()
        }
    }
}