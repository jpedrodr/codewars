package com.jpedrodr.codewars.app.di

import com.jpedrodr.codewars.domain.Domain
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStoreProvider

interface AppComponent {

    val domain: Domain

    val keyValueStoreProvider: KeyValueStoreProvider
}