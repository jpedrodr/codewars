package com.jpedrodr.codewars.app.platform.keyvaluestore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStore
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStoreProvider

private val storeMap = mutableMapOf<String, KeyValueStore>()

class AndroidKeyValueStoreProvider(
    private val context: Context
) : KeyValueStoreProvider {

    override fun store(owner: String): KeyValueStore = synchronized(owner) {
        val keyValueStore = storeMap[owner]

        if (keyValueStore != null) {
            return keyValueStore
        }

        return AndroidKeyValueStore(preferencesDataStore(owner).getValue(context, ::context)).also {
            storeMap[owner] = it
        }
    }
}