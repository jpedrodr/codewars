package com.jpedrodr.codewars.app.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.jpedrodr.codewars.app.ui.BaseActivity

interface ActivityComponent : AppComponent {

    val activity: BaseActivity

    val viewModelStoreOwner: ViewModelStoreOwner

    val viewModelProvider: ViewModelProvider
}