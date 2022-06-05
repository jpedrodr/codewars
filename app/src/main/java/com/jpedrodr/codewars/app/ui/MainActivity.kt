package com.jpedrodr.codewars.app.ui

import android.os.Bundle
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.commons.Tagged

class MainActivity : BaseActivity(), Tagged {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}