package com.jpedrodr.codewars.app.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ActivityCompositionRoot(
    appComponent: AppComponent,
    private val activity: FragmentActivity
) : ActivityComponent, AppComponent by appComponent {

    override val viewModelProvider: ViewModelProvider by lazy {
        ViewModelProvider(
            viewModelStoreOwner,
            ViewModelFactory(this, activity)
        )
    }

    override val viewModelStoreOwner: ViewModelStoreOwner = activity
}