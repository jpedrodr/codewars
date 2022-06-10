package com.jpedrodr.codewars.lib.model

import com.jpedrodr.codewars.domain.model.CompletedChallenge

class CompletedChallengesResponse(
    totalPages: Int,
    totalItems: Int,
    data: List<CompletedChallenge>
)