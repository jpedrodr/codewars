package com.jpedrodr.codewars.domain.usecase

import com.jpedrodr.codewars.lib.interactor.OfflineModeInteractor

class OfflineModeChangesUseCase(
    private val offlineModeInteractor: OfflineModeInteractor
) {
    operator fun invoke() = offlineModeInteractor.offlineModeFlow
}