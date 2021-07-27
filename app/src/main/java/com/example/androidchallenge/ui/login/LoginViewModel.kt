package com.example.androidchallenge.ui.login

import com.example.androidchallenge.base.BaseViewModel
import com.example.androidchallenge.data.Auth
import com.example.androidchallenge.data.Result
import com.example.androidchallenge.network.DataState
import com.example.androidchallenge.network.repositories.MeditationRepository
import io.paperdb.Paper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class LoginViewModel(private val meditationRepository: MeditationRepository): BaseViewModel() {

    fun getData() = flow {
        meditationRepository.data().collect {
            when (it) {
                is DataState.Success -> emit(it.data)
                is DataState.Exception -> exceptionObserver.value = it
                is DataState.Loading -> loadingObserver.value = DataState.Loading
            }
        }
    }

    fun saveLoginData(auth: Auth) {
        Paper.book().write("auth", auth)
    }

    fun saveData(result: Result?) {
        Paper.book().write("data", result)
    }
}