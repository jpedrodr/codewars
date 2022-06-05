package com.jpedrodr.codewars.app.ui

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.jpedrodr.codewars.app.di.FragmentCompositionRoot

open class BaseFragment(
    @LayoutRes fragmentLayoutRes: Int
) : Fragment(fragmentLayoutRes) {

    val compositionRoot by lazy {
        FragmentCompositionRoot((requireActivity() as BaseActivity).compositionRoot, this)
    }

}

inline fun <reified T : ViewModel> BaseFragment.viewModel(): Lazy<T> =
    lazy { compositionRoot.viewModelProvider.get(T::class.java) }