package com.jpedrodr.codewars.app.ui.challenge

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ChallengeDateFormatter {
    private val isoFormatter = DateTimeFormatter.ISO_DATE_TIME
    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    fun format(dateString: String): String {
        val localDate = LocalDate.parse(dateString, isoFormatter)
        return localDate.format(formatter)
    }
}