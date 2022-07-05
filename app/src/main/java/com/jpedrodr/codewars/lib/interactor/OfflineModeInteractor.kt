package com.jpedrodr.codewars.lib.interactor

import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.repository.ChallengeRepository
import com.jpedrodr.codewars.lib.repository.OfflineModeRepository

/**
 * Interactor responsible for offline mode
 */
class OfflineModeInteractor(
    private val offlineModeRepository: OfflineModeRepository,
    private val challengeRepository: ChallengeRepository
) : Tagged {

    /**
     * Flow to collect the offline mode state changes
     */
    val offlineModeFlow = offlineModeRepository.offlineModeFlow

    /**
     * Sets the offline mode to [isOffline]
     */
    fun setOfflineMode(isOffline: Boolean) {
        val isFirstTimeOnline = offlineModeRepository.isFirstTimeOnline()
        logger.d(TAG, "setOfflineMode - isOffline=$isOffline, isFirstTimeOnline=$isFirstTimeOnline")
        val currentOfflineState = offlineModeRepository.isOffline()
        offlineModeRepository.setOfflineMode(isOffline)

        if (isFirstTimeOnline) {
            offlineModeRepository.setFirstTimeOnline(false)
        }

        if (!isOffline && currentOfflineState && !isFirstTimeOnline) {
            // if we were offline but no longer are, refresh data if needed
            challengeRepository.refreshCompletedChallengesIfNeeded()
        }
    }
}