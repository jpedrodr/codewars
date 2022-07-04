package com.jpedrodr.codewars.app.ui.challenge.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jpedrodr.codewars.app.model.CompletedChallenge
import com.jpedrodr.codewars.app.model.mapper.mapToUi
import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.domain.usecase.GetCompletedChallengesUseCase
import kotlinx.coroutines.launch

class ChallengeListViewModel(
    private val getCompletedChallengesUseCase: GetCompletedChallengesUseCase
) : ViewModel(), Tagged {

    private val _completedChallenges = MutableLiveData<List<CompletedChallenge>>()
    val completedChallenges: LiveData<List<CompletedChallenge>> = _completedChallenges

    fun loadChallenges() {
        viewModelScope.launch {
            val challenges = getCompletedChallengesUseCase()
            logger.d(TAG, "loadChallenges - challenges=${challenges.size}")
            _completedChallenges.value = challenges.mapToUi()
        }
    }

    fun refreshChallenges() {
        viewModelScope.launch {
            val challenges = getCompletedChallengesUseCase(true)
            logger.d(TAG, "loadChallenges - challenges=${challenges.size}")
            _completedChallenges.value = challenges.mapToUi()
        }
    }


}