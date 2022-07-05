package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.lib.interactor.OfflineModeInteractor

/**
 * Use case responsible for setting the offline mode state
 */
class SetOfflineModeUseCase(
    private val offlineModeInteractor: OfflineModeInteractor
) {
    operator fun invoke(isOffline: Boolean) {
        offlineModeInteractor.setOfflineMode(isOffline)
    }
}