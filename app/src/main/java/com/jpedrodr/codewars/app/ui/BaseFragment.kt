package com.jpedrodr.codewars.app.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.jpedrodr.codewars.app.di.FragmentCompositionRoot
import com.jpedrodr.codewars.commons.Tagged

open class BaseFragment : Fragment(), Tagged {

    val compositionRoot by lazy {
        FragmentCompositionRoot((requireActivity() as BaseActivity).compositionRoot, this)
    }

}

inline fun <reified T : ViewModel> BaseFragment.viewModel(): Lazy<T> =
    lazy { compositionRoot.viewModelProvider[T::class.java] }