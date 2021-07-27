package com.example.androidchallenge.network

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<out T>(val data: T?) : DataState<T>()
    data class Exception(val exception: Pair<Int, String?>) : DataState<Nothing>()
    override fun toString(): String {
        return when (this) {
            is Loading -> "Loading[]"
            is Success -> "Success[data: $data]"
            is Exception -> "ShowFullInfo[error: ${exception.first} -> ${exception.second}"
        }
    }
}
