package com.jpedrodr.codewars.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jpedrodr.codewars.domain.model.CompletedChallenge

class MainViewModel : ViewModel() {

    private val _openChallenge = MutableLiveData<CompletedChallenge>()

    // TODO must be an event
    val openChallenge: LiveData<CompletedChallenge> = _openChallenge

    /**
     * Signals to open the details of the given fragment
     */
    fun openChallenge(challenge: CompletedChallenge) {
        _openChallenge.value = challenge
    }
}