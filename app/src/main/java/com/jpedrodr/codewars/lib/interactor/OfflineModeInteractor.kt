package com.jpedrodr.codewars.lib.interactor

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.repository.ChallengeRepository
import com.jpedrodr.codewars.lib.repository.OfflineModeRepository

class OfflineModeInteractor(
    private val offlineModeRepository: OfflineModeRepository,
    private val challengeRepository: ChallengeRepository
) : Tagged {

    private var isFirstTimeOnline = true

    fun setOfflineMode(isOffline: Boolean) {
        logger.d(TAG, "setOfflineMode - isOffline=$isOffline")
        val currentOfflineState = offlineModeRepository.isOffline()
        offlineModeRepository.setOfflineMode(isOffline)

        if (!isOffline && currentOfflineState && !isFirstTimeOnline) {
            // if we were offline but no longer are, refresh data if needed
            challengeRepository.refreshDataIfNeeded()
            isFirstTimeOnline = false
        }
    }

    fun isOffline() = offlineModeRepository.isOffline()
}