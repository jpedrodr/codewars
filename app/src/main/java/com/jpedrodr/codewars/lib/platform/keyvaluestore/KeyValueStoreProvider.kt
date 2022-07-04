package com.jpedrodr.codewars.lib.platform.keyvaluestore

interface KeyValueStoreProvider {

    fun store(owner: String): KeyValueStore
}