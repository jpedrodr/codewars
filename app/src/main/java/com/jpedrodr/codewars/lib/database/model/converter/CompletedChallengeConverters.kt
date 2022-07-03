package com.jpedrodr.codewars.lib.database.model.converter

import androidx.room.TypeConverter

private const val LIST_SEPARATOR = ","

class CompletedChallengeConverters {

    @TypeConverter
    fun fromStringListToString(list: List<String>): String {
        return list.joinToString(separator = LIST_SEPARATOR)
    }

    @TypeConverter
    fun fromStringToStringList(listString: String): List<String> {
        return listString.split(LIST_SEPARATOR)
    }
}