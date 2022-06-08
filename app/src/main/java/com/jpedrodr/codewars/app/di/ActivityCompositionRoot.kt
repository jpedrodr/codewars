package com.jpedrodr.codewars.app.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.jpedrodr.codewars.app.ui.BaseActivity

class ActivityCompositionRoot(
    appComponent: AppComponent,
    override val activity: BaseActivity
) : ActivityComponent, AppComponent by appComponent {

    override val viewModelProvider: ViewModelProvider by lazy {
        ViewModelProvider(
            viewModelStoreOwner,
            ViewModelFactory(this, activity)
        )
    }

    override val viewModelStoreOwner: ViewModelStoreOwner = activity
}