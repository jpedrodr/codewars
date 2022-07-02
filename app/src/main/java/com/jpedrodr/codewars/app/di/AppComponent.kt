package com.jpedrodr.codewars.app.di

import com.jpedrodr.codewars.app.network.ConnectivityManager
import com.jpedrodr.codewars.domain.Domain

interface AppComponent {

    val domain: Domain

    val connectivityManager: ConnectivityManager
}