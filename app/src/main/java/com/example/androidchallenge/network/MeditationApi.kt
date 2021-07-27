package com.example.androidchallenge.network

import com.example.androidchallenge.data.Meditation
import com.example.androidchallenge.data.Result
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface MeditationApi {

    @GET("a07152f5-775c-11eb-a533-c90b9d55001f")
    suspend fun sampleCall(): Response<Result>
}