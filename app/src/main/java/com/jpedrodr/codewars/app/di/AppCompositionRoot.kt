package com.jpedrodr.codewars.app.di

import com.jpedrodr.codewars.app.AndroidLogger
import com.jpedrodr.codewars.app.CodeWarsApp
import com.jpedrodr.codewars.app.network.ConnectivityManager
import com.jpedrodr.codewars.commons.Logger
import com.jpedrodr.codewars.domain.Domain

class AppCompositionRoot(private val app: CodeWarsApp) : AppComponent {

    init {
        Logger.injectLogger(AndroidLogger())
    }

    fun initDependencies() {
        connectivityManager = ConnectivityManager(app)
        domain = Domain(connectivityManager)
    }

    override lateinit var domain: Domain

    override lateinit var connectivityManager: ConnectivityManager

}