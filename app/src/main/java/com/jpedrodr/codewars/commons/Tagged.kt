package com.jpedrodr.codewars.commons

interface Tagged {
    val TAG: String
        get() = javaClass.simpleName

    val logger: Logger
        get() = Logger.get()

}


