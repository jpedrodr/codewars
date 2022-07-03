package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.lib.interactor.OfflineModeInteractor

class SetOfflineModeUseCase(
    private val offlineModeInteractor: OfflineModeInteractor
) {
    operator fun invoke(isOffline: Boolean) {
        offlineModeInteractor.setOfflineMode(isOffline)
    }
}