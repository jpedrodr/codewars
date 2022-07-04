package com.jpedrodr.codewars.app.ui.challenge.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpedrodr.codewars.app.model.CompletedChallenge
import com.jpedrodr.codewars.app.model.mapper.mapToUi
import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import com.jpedrodr.codewars.domain.usecase.OfflineModeChangesUseCase
import kotlinx.coroutines.launch

class ChallengeListViewModel(
    private val getCompletedChallengesUseCase: GetCompletedChallengesUseCase,
    private val offlineModeChangesUseCase: OfflineModeChangesUseCase
) : ViewModel(), Tagged {

    private val _completedChallenges = MutableLiveData<List<CompletedChallenge>>()
    val completedChallenges: LiveData<List<CompletedChallenge>> = _completedChallenges

    private val _challengesLoading = MutableLiveData<Boolean>()
    val challengesLoading: LiveData<Boolean> = _challengesLoading

    init {
        viewModelScope.launch {
            trackOfflineModeChanges()
        }
    }

    fun loadChallenges() {
        doLoadChallenges()
    }

    fun refreshChallenges() {
        doLoadChallenges(true)
    }

    private fun doLoadChallenges(forceUpdate: Boolean = false) {
        viewModelScope.launch {
            _challengesLoading.value = true
            val challenges = getCompletedChallengesUseCase(forceUpdate)
            logger.d(TAG, "loadChallenges - challenges=${challenges.size}")
            _challengesLoading.value = false
            _completedChallenges.value = challenges.mapToUi()
        }
    }

    private fun trackOfflineModeChanges() {
        viewModelScope.launch {
            offlineModeChangesUseCase().collect {
                logger.d(TAG, "isOffline=$it, empty=${_completedChallenges.value.isNullOrEmpty()}")
                if (!it && _completedChallenges.value.isNullOrEmpty()) {
                    refreshChallenges()
                }
            }
        }
    }
}