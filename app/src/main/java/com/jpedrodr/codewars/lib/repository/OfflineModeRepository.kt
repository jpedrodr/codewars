package com.jpedrodr.codewars.lib.repository

import com.jpedrodr.codewars.commons.Tagged
import java.util.concurrent.atomic.AtomicBoolean

class OfflineModeRepository: Tagged {
    private var isOfflineMode: AtomicBoolean = AtomicBoolean(true)
    private var isFirstTimeOnline: AtomicBoolean = AtomicBoolean(true)

    fun setOfflineMode(isOffline: Boolean) {
        logger.d(TAG, "setOfflineMode - isOffline=$isOffline")
        isOfflineMode.set(isOffline)
    }

    fun isOffline() = isOfflineMode.get()

    fun isFirstTimeOnline() = isFirstTimeOnline.get()

    fun setFirstTimeOnline(isFirstTimeOffline: Boolean) {
        logger.d(TAG, "setFirstTimeOnline - isFirstTimeOffline=$isFirstTimeOffline")
        isFirstTimeOnline.set(isFirstTimeOffline)
    }
}