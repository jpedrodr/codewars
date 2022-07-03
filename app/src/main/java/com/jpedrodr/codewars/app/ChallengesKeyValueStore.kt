package com.jpedrodr.codewars.app

import android.content.Context
import android.content.SharedPreferences
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.commons.INVALID_TIMESTAMP

private const val COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY =
    "COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY"

object ChallengesKeyValueStore {

    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.getString(R.string.shared_preferences_file_key),
            Context.MODE_PRIVATE
        )
    }

    fun getCompletedChallengesLastRefreshTimestamp(): Long {
        if (!::context.isInitialized) {
            throw IllegalAccessError("Can't access key store without calling init")
        }

        return sharedPreferences.getLong(
            COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY,
            INVALID_TIMESTAMP
        )
    }

    fun setCompletedChallengesLastRefreshTimestamp(timestamp: Long) {
        if (!::context.isInitialized) {
            throw IllegalAccessError("Can't access key store without calling init")
        }

        sharedPreferences.edit().putLong(COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY, timestamp)
            .commit()
    }
}