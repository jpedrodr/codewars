package com.jpedrodr.codewars.app.di

import com.jpedrodr.codewars.app.AndroidLogger
import com.jpedrodr.codewars.commons.Logger
import com.jpedrodr.codewars.domain.Domain

class AppCompositionRoot : AppComponent {

    init {
        Logger.injectLogger(AndroidLogger())
    }

    fun initDependencies() {
        domain = Domain()
    }

    override lateinit var domain: Domain

}