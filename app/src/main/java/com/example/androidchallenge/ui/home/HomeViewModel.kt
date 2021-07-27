package com.example.androidchallenge.ui.home

import com.example.androidchallenge.base.BaseViewModel
import com.example.androidchallenge.data.Auth
import com.example.androidchallenge.data.Result
import com.example.androidchallenge.network.DataState
import com.example.androidchallenge.network.repositories.MeditationRepository
import io.paperdb.Paper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class HomeViewModel(private val meditationRepository: MeditationRepository) : BaseViewModel() {

    fun getAuthData() = Paper.book().read<Auth>("auth")

    fun getLocalData() = flow {
        val data = Paper.book().read<Result?>("data")
        if (data != null) emit(data)
        else {
            getRemoteData().collect {
                emit(it)
            }
        }
    }

    private fun getRemoteData() = flow {
        meditationRepository.data().collect {
            when (it) {
                is DataState.Success -> emit(it.data)
                is DataState.Exception -> exceptionObserver.value = it
                is DataState.Loading -> loadingObserver.value = DataState.Loading
            }
        }
    }


    fun getMeditations() = flow {
        val meditations = meditationRepository.meditations()
        if (meditations != null) emit(meditations)
        else {
            meditationRepository.data().collect {
                emit(meditationRepository.meditations())
            }
        }
    }

    fun getStories() = flow {
        val stories = meditationRepository.stories()
        if (stories != null) emit(stories)
        else {
            meditationRepository.data().collect {
                emit(meditationRepository.stories())
            }
        }
    }
}