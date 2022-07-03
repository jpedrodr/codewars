package com.jpedrodr.codewars.lib

import com.jpedrodr.codewars.lib.database.AppDatabase
import com.jpedrodr.codewars.lib.network.ChallengeApi
import com.jpedrodr.codewars.lib.network.RetrofitBuilder
import com.jpedrodr.codewars.lib.repository.ChallengeRepository
import com.jpedrodr.codewars.lib.repository.OfflineModeRepository
import retrofit2.Retrofit

internal class LibCompositionRoot(
    private val database: AppDatabase
) : Lib {

    override val challengeRepository: ChallengeRepository
        get() = ChallengeRepository(
            challengeApi,
            database.completedChallengeDao(),
            offlineModeRepository
        )

    override val offlineModeRepository: OfflineModeRepository by lazy { OfflineModeRepository() }

    private val retrofit: Retrofit = RetrofitBuilder.getRetrofit(offlineModeRepository)

    private val challengeApi by lazy {
        retrofit.create(ChallengeApi::class.java)
    }

}