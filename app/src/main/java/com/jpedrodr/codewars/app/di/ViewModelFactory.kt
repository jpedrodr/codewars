package com.jpedrodr.codewars.app.di

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.jpedrodr.codewars.app.ui.MainViewModel
import com.jpedrodr.codewars.app.ui.challenge.list.ChallengeListViewModel
import com.jpedrodr.codewars.commons.Tagged

class ViewModelFactory(
    activityComponent: ActivityComponent,
    savedStateRegistryOwner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, defaultArgs), Tagged {

    private val domain = activityComponent.domain

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            ChallengeListViewModel::class.java -> ChallengeListViewModel(
                domain.getCompletedChallengesUseCase,
                domain.offlineModeChangesUseCase
            )
            MainViewModel::class.java -> MainViewModel()
            else -> throw Exception("ViewModel not supported $this")
        } as T
    }
}