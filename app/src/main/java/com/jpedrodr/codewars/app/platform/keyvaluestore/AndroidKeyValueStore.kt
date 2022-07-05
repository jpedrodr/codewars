package com.jpedrodr.codewars.app.platform.keyvaluestore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.jpedrodr.codewars.commons.Tagged
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStore
import kotlinx.coroutines.flow.first

class AndroidKeyValueStore(
    private val dataStore: DataStore<Preferences>
) : KeyValueStore, Tagged {

    override suspend fun <T : Any> write(key: String, value: T?, valueClass: Class<T>) {
        dataStore.edit {
            val preferenceKey = getKey(valueClass, key) ?: return@edit
            if (value == null) {
                it.remove(preferenceKey)
            } else {
                it[preferenceKey] = value
            }
        }
    }

    override suspend fun <T : Any> read(key: String, defaultValue: T, valueClass: Class<T>): T {
        val preferencesKey = getKey(valueClass, key) ?: return defaultValue

        return dataStore.data.first()[preferencesKey] ?: defaultValue
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getKey(keyClass: Class<T>, key: String): Preferences.Key<T>? {
        return when (keyClass) {
            Long::class.java -> longPreferencesKey(key)
            else -> {
                logger.wtf(TAG, "getKey - Unsupp")
                null
            }
        } as Preferences.Key<T>?
    }
}