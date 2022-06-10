package com.dedechandran.storiesapps.common

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class ApiError<T>(val code: Int, val message: String): Result<T>()
    data class Failure<T>(val message: String): Result<T>()
}
