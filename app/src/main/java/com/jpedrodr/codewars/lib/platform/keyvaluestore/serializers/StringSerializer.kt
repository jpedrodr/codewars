package com.jpedrodr.codewars.lib.platform.keyvaluestore.serializers

interface StringSerializer<T> {

    fun toString(value: T): String

    fun fromString(value: String): T
}