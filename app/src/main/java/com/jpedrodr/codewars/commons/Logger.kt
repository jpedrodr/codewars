package com.jpedrodr.codewars.commons

interface Logger {

    companion object {
        private lateinit var logger: Logger

        fun injectLogger(logger: Logger) {
            this.logger = logger
        }

        fun get(): Logger = logger
    }

    fun v(tag: String, message: String)
    fun d(tag: String, message: String)
    fun i(tag: String, message: String)
    fun w(tag: String, message: String)
    fun e(tag: String, message: String)
    fun wtf(tag: String, message: String)
}