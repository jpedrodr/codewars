package com.jpedrodr.codewars.lib.network

abstract class Result<T> {
    fun unwrapSuccess(): T {
        return when (this) {
            is Error -> throw IllegalAccessException("Can't unwrap an error!")
            is Success -> data
            else -> {
                throw NotImplementedError("$this should be handled in unwrapSuccess")
            }
        }
    }
}

class Success<T>(val data: T) : Result<T>()

class Error<T>(val exception: Exception) : Result<T>()

