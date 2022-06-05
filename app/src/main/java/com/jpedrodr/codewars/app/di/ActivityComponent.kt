package com.jpedrodr.codewars.app.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

interface ActivityComponent : AppComponent {

    val viewModelStoreOwner: ViewModelStoreOwner

    val viewModelProvider: ViewModelProvider
}