package com.jpedrodr.codewars.app.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.jpedrodr.codewars.app.ChallengesKeyValueStore
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

    override fun onPause() {
        super.onPause()
        val timestamp =
            _compositionRoot.domain.completedChallengesRefreshTimestampUseCase.getTimestamp()

        // This KeyValueStore should be abstracted via an interface and should be passed to lib so lib can change it directly since activities shouldn't handle this logic
        ChallengesKeyValueStore.setCompletedChallengesLastRefreshTimestamp(timestamp)
    }

    override fun onStart() {
        super.onStart()
        // This KeyValueStore should be abstracted via an interface and should be passed to lib so lib can change it directly since activities shouldn't handle this logic
        val timestamp = ChallengesKeyValueStore.getCompletedChallengesLastRefreshTimestamp()
        _compositionRoot.domain.completedChallengesRefreshTimestampUseCase.setTimestamp(timestamp)
    }
}

inline fun <reified T : ViewModel> BaseActivity.viewModel(): Lazy<T> =
    lazy { compositionRoot.viewModelProvider[T::class.java] }