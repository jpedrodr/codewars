package com.jpedrodr.codewars.app.network

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityManager(private val context: Context) {

    private val connectivityManager by lazy { context.getSystemService(ConnectivityManager::class.java) }

    fun isOnline(): Boolean {
        return connectivityManager.activeNetwork != null
    }
}