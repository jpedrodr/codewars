package com.jpedrodr.codewars.lib.network

typealias Result<T> = Either<T, Exception>

fun <T, E> Either<T, E>.unwrapSuccess(): T {
    return when (this) {
        is Error -> throw IllegalAccessException("Can't unwrap an error!")
        is Success -> data
    }
}

sealed class Either<out T, out E>
data class Success<out T>(val data: T) : Either<T, Nothing>()
data class Error<out E>(val data: E) : Either<Nothing, E>()
