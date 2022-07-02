package com.jpedrodr.codewars.app

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import com.jpedrodr.codewars.app.di.AppCompositionRoot
import com.jpedrodr.codewars.commons.Tagged

class CodeWarsApp : Application(), Tagged {

    private val _appCompositionRoot = AppCompositionRoot()

    val appCompositionRoot: AppCompositionRoot
        get() = _appCompositionRoot

    override fun onCreate() {
        super.onCreate()

        logger.i(TAG, "****************************** app start ******************************")

        _appCompositionRoot.initDependencies()

        trackConnectivity()
    }

    private fun trackConnectivity() {
        val connectivityManager = getSystemService(ConnectivityManager::class.java)

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                logger.d(TAG, "trackConnectivity - onAvailable")
                _appCompositionRoot.domain.setOfflineModeUseCase(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                logger.d(TAG, "trackConnectivity - onLost")
                _appCompositionRoot.domain.setOfflineModeUseCase(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                logger.d(TAG, "trackConnectivity - onUnavailable")
                _appCompositionRoot.domain.setOfflineModeUseCase(false)
            }
        })
    }
}