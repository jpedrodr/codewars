package com.jpedrodr.codewars.lib.platform.keyvaluestore

import com.jpedrodr.codewars.lib.platform.keyvaluestore.serializers.StringSerializer

interface KeyValueStore {

    suspend fun <T : Any> write(key: String, value: T?, serializer: StringSerializer<T>)

    suspend fun <T : Any> read(key: String, serializer: StringSerializer<T>): T?
}