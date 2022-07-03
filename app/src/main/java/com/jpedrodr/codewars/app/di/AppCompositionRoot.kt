package com.jpedrodr.codewars.app.di

import com.jpedrodr.codewars.app.AndroidLogger
import com.jpedrodr.codewars.commons.Logger
import com.jpedrodr.codewars.domain.Domain
import com.jpedrodr.codewars.lib.database.AppDatabase

class AppCompositionRoot : AppComponent {

    init {
        Logger.injectLogger(AndroidLogger())
    }

    fun initDependencies(database: AppDatabase) {
        domain = Domain(database)
    }

    override lateinit var domain: Domain

}