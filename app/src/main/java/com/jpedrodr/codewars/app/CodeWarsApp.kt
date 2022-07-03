package com.jpedrodr.codewars.app

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.room.Room
import com.jpedrodr.codewars.app.di.AppCompositionRoot
import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.database.AppDatabase

class CodeWarsApp : Application(), LifecycleEventObserver, Tagged {

    private val _appCompositionRoot = AppCompositionRoot()

    val appCompositionRoot: AppCompositionRoot
        get() = _appCompositionRoot

    override fun onCreate() {
        super.onCreate()

        logger.i(TAG, "****************************** app start ******************************")

        val database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "asd").build()
        _appCompositionRoot.initDependencies(database)
        trackConnectivity()

        initChallengesSharedPreferences()
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        logger.d("test009", "onStateChanged - event=$event")

        if (event == Lifecycle.Event.ON_PAUSE) {
            val timestamp =
                _appCompositionRoot.domain.completedChallengesRefreshTimestampUseCase.getTimestamp()
            ChallengesKeyValueStore.setCompletedChallengesLastRefreshTimestamp(timestamp)
        }
    }

    private fun initChallengesSharedPreferences() {
        ChallengesKeyValueStore.init(context = applicationContext)
        val timestamp = ChallengesKeyValueStore.getCompletedChallengesLastRefreshTimestamp()
        _appCompositionRoot.domain.completedChallengesRefreshTimestampUseCase.setTimestamp(timestamp)
    }

    private fun trackConnectivity() {
        val connectivityManager = getSystemService(ConnectivityManager::class.java)

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                logger.d(TAG, "trackConnectivity - onAvailable")
                _appCompositionRoot.domain.setOfflineModeUseCase(false)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                logger.d(TAG, "trackConnectivity - onLost")
                _appCompositionRoot.domain.setOfflineModeUseCase(true)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                logger.d(TAG, "trackConnectivity - onUnavailable")
                _appCompositionRoot.domain.setOfflineModeUseCase(true)
            }
        })
    }
}