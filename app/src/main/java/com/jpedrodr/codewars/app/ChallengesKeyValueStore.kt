package com.jpedrodr.codewars.app

import android.content.Context
import android.content.SharedPreferences
import com.jpedrodr.codewars.R
import com.jpedrodr.codewars.commons.INVALID_TIMESTAMP
import com.jpedrodr.codewars.commons.Tagged

private const val COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY =
    "COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY"

/**
 * This KeyValueStore should be abstracted via an interface and should be passed to lib so lib can change it directly
 */
object ChallengesKeyValueStore : Tagged {

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

        val timestamp = sharedPreferences.getLong(
            COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY,
            INVALID_TIMESTAMP
        )

        logger.d(TAG, "getCompletedChallengesLastRefreshTimestamp - timestamp=$timestamp")

        return timestamp
    }

    fun setCompletedChallengesLastRefreshTimestamp(timestamp: Long) {
        if (!::context.isInitialized) {
            throw IllegalAccessError("Can't access key store without calling init")
        }

        logger.d(TAG, "setCompletedChallengesLastRefreshTimestamp - timestamp=$timestamp")
        sharedPreferences.edit().putLong(COMPLETED_CHALLENGES_LAST_REFRESH_TIMESTAMP_KEY, timestamp)
            .commit()
    }
}