package com.jpedrodr.codewars.app

import android.app.Application
import com.jpedrodr.codewars.app.di.AppCompositionRoot
import com.jpedrodr.codewars.commons.Tagged

class CodeWarsApp : Application(), Tagged {

    private val _appCompositionRoot = AppCompositionRoot(this)

    val appCompositionRoot: AppCompositionRoot
        get() = _appCompositionRoot

    override fun onCreate() {
        super.onCreate()

        logger.i(TAG, "****************************** app start ******************************")

        _appCompositionRoot.initDependencies()
    }
}