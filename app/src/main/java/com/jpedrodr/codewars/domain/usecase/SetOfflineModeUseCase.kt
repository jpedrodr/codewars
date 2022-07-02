package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.lib.repository.OfflineModeRepository

class SetOfflineModeUseCase(
    private val offlineModeRepository: OfflineModeRepository
) {
    operator fun invoke(isOffline: Boolean) {
        offlineModeRepository.setOfflineMode(isOffline)
    }
}