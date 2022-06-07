package com.jpedrodr.codewars.app.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.jpedrodr.codewars.app.CodeWarsApp
import com.jpedrodr.codewars.app.di.ActivityComponent
import com.jpedrodr.codewars.app.di.ActivityCompositionRoot
import com.jpedrodr.codewars.commons.Tagged

open class BaseActivity : AppCompatActivity(), Tagged {

    private val _compositionRoot by lazy {
        ActivityCompositionRoot(
            (application as CodeWarsApp).appCompositionRoot,
            this
        )
    }

    val compositionRoot: ActivityComponent
        get() = _compositionRoot
}

inline fun <reified T : ViewModel> BaseActivity.viewModel(): Lazy<T> =
    lazy { compositionRoot.viewModelProvider.get(T::class.java) }