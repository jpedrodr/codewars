package com.jpedrodr.codewars.lib.repository

import java.util.concurrent.atomic.AtomicBoolean

class OfflineModeRepository {
    private var isOfflineMode: AtomicBoolean = AtomicBoolean(false)

    fun setOfflineMode(isOffline: Boolean) {
        isOfflineMode.set(isOffline)
    }

    fun isOffline() = isOfflineMode.get()
}