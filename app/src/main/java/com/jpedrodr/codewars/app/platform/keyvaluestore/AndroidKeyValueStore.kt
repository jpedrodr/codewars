package com.jpedrodr.codewars.app.platform.keyvaluestore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStore
import com.jpedrodr.codewars.lib.platform.keyvaluestore.serializers.StringSerializer
import kotlinx.coroutines.flow.first

class AndroidKeyValueStore(
    private val dataStore: DataStore<Preferences>
) : KeyValueStore {

    override suspend fun <T : Any> write(key: String, value: T?, serializer: StringSerializer<T>) {
        dataStore.edit {
            val preferenceKey = stringPreferencesKey(key)
            if (value == null) {
                it.remove(preferenceKey)
            } else {
                it[preferenceKey] = serializer.toString(value)
            }
        }
    }

    override suspend fun <T : Any> read(key: String, serializer: StringSerializer<T>): T? {
        return dataStore.data.first()[stringPreferencesKey(key)]?.let {
            serializer.fromString(it)
        }
    }
}