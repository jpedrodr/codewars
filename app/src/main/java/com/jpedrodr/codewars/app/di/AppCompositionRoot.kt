package com.jpedrodr.codewars.app.di

import com.jpedrodr.codewars.app.AndroidLogger
import com.jpedrodr.codewars.app.CodeWarsApp
import com.jpedrodr.codewars.app.platform.keyvaluestore.AndroidKeyValueStoreProvider
import com.jpedrodr.codewars.commons.Logger
import com.jpedrodr.codewars.domain.Domain
import com.jpedrodr.codewars.lib.database.AppDatabase
import com.jpedrodr.codewars.lib.platform.keyvaluestore.KeyValueStoreProvider

class AppCompositionRoot(
    private val app: CodeWarsApp
) : AppComponent {

    init {
        Logger.injectLogger(AndroidLogger())
    }

    fun initDependencies(database: AppDatabase) {
        keyValueStoreProvider = AndroidKeyValueStoreProvider(app)
        domain = Domain(database, keyValueStoreProvider)
    }

    override lateinit var domain: Domain

    override lateinit var keyValueStoreProvider: KeyValueStoreProvider
}