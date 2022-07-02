package com.jpedrodr.codewars.lib

import com.jpedrodr.codewars.lib.network.ChallengeApi
import com.jpedrodr.codewars.lib.repository.ChallengeRepository
import com.jpedrodr.codewars.lib.repository.OfflineModeRepository
import com.jpedrodr.codewars.lib.network.RetrofitBuilder
import retrofit2.Retrofit

internal class LibCompositionRoot : Lib {

    override val challengeRepository: ChallengeRepository
        get() = ChallengeRepository(challengeApi)

    override val offlineModeRepository: OfflineModeRepository
        get() = OfflineModeRepository()

    private val retrofit: Retrofit = RetrofitBuilder.getRetrofit(offlineModeRepository)

    private val challengeApi by lazy {
        retrofit.create(ChallengeApi::class.java)
    }

}