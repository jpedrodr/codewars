package com.jpedrodr.codewars.app.di

import com.jpedrodr.codewars.app.CodeWarsApp
import com.jpedrodr.codewars.domain.Domain

class AppCompositionRoot(
    private val app: CodeWarsApp
) : AppComponent {

    fun initDependencies() {
        domain = Domain()
    }


    override lateinit var domain: Domain


}