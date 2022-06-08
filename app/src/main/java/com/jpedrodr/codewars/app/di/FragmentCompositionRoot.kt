package com.jpedrodr.codewars.app.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class FragmentCompositionRoot(
    private val activityComponent: ActivityComponent,
    fragment: Fragment,
) : ActivityComponent by activityComponent {

    override val viewModelProvider: ViewModelProvider by lazy {
        ViewModelProvider(
            viewModelStoreOwner,
            ViewModelFactory(this, fragment)
        )
    }

    override val viewModelStoreOwner: ViewModelStoreOwner = fragment

    val activityViewModelProvider by lazy {
        ViewModelProvider(
            activity.viewModelStore,
            ViewModelFactory(this, activity)
        )
    }
}