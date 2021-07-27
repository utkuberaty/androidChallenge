package com.example.androidchallenge.network.repositories

import com.example.androidchallenge.data.Result
import com.example.androidchallenge.network.DataState
import com.example.androidchallenge.network.MeditationApi
import com.example.androidchallenge.network.networkCall
import io.paperdb.Paper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class MeditationRepository(private val meditationApi: MeditationApi) {

    fun data() = flow {
        networkCall { meditationApi.sampleCall() }.collect {
            when (it) {
                is DataState.Success -> emit(DataState.Success(it.data))
                is DataState.Loading -> emit(it)
                is DataState.Exception -> emit(it)
            }
        }
    }


    fun meditations() = Paper.book().read<Result?>("data")?.meditations

    fun stories() = Paper.book().read<Result?>("data")?.stories

}