package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.lib.interactor.OfflineModeInteractor

/**
 * Use case responsible for providing offline mode states
 */
class OfflineModeChangesUseCase(
    private val offlineModeInteractor: OfflineModeInteractor
) {
    operator fun invoke() = offlineModeInteractor.offlineModeFlow
}