package com.jpedrodr.codewars.lib.repository

import com.jpedrodr.codewars.commons.Tagged
import java.util.concurrent.atomic.AtomicBoolean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * Repository responsible for holding and managing offline mode data
 */
class OfflineModeRepository(
    private val offlineModeScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) : Tagged {
    private var isOfflineMode: AtomicBoolean = AtomicBoolean(true)
    private var isFirstTimeOnline: AtomicBoolean = AtomicBoolean(true)

    private val _offlineModeFlow = MutableSharedFlow<Boolean>()
    val offlineModeFlow = _offlineModeFlow.asSharedFlow()

    /**
     * Sets the offline mode to [isOffline]
     */
    fun setOfflineMode(isOffline: Boolean) {
        logger.d(TAG, "setOfflineMode - isOffline=$isOffline")
        isOfflineMode.set(isOffline)

        offlineModeScope.launch {
            _offlineModeFlow.emit(isOffline)
        }
    }

    /**
     * Returns whether or not we are in offline mode
     */
    fun isOffline() = isOfflineMode.get()

    /**
     * Returns whether or not we are online for the first time
     */
    fun isFirstTimeOnline() = isFirstTimeOnline.get()

    /**
     * Sets the first time online to [isFirstTimeOffline]
     */
    fun setFirstTimeOnline(isFirstTimeOffline: Boolean) {
        logger.d(TAG, "setFirstTimeOnline - isFirstTimeOffline=$isFirstTimeOffline")
        isFirstTimeOnline.set(isFirstTimeOffline)
    }
}