package com.jpedrodr.codewars.lib

import com.jpedrodr.codewars.app.network.RetrofitBuilder
import com.jpedrodr.codewars.lib.network.ChallengeApi
import com.jpedrodr.codewars.lib.repository.ChallengeRepository
import retrofit2.Retrofit

internal class LibCompositionRoot : Lib {

    override val challengeRepository: ChallengeRepository
        get() = ChallengeRepository(challengeApi)

    private val retrofit: Retrofit = RetrofitBuilder.getRetrofit()

    private val challengeApi by lazy {
        retrofit.create(ChallengeApi::class.java)
    }

}