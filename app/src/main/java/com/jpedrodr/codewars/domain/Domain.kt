package com.jpedrodr.codewars.domain

interface Domain {

    companion object {
        operator fun invoke(): Domain = DomainCompositionRoot()
    }


}