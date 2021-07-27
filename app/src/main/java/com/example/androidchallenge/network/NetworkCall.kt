package com.example.androidchallenge.network

import android.util.Log
import kotlinx.coroutines.flow.flow
import retrofit2.Response


suspend fun <T : Any> networkCall(call: suspend () -> Response<T>) = flow {
    try {
        emit(DataState.Loading)
        val response = call.invoke()
        if (response.isSuccessful) {
            Log.i("networkCall", DataState.Success(response.body()).toString())

            emit(DataState.Success(response.body()))
        } else {
            emit(DataState.Exception(response.code() to response.message()))
        }
    } catch (ex: Exception) {
        emit(DataState.Exception(-1 to ex.localizedMessage))
    }
}
