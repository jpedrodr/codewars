package com.jpedrodr.codewars.lib.platform.keyvaluestore


interface KeyValueStore {

    suspend fun <T : Any> write(key: String, value: T?, valueClass: Class<T>)

    suspend fun <T : Any> read(key: String, defaultValue: T, valueClass: Class<T>): T
}