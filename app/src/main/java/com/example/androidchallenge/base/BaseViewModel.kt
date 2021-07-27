package com.example.androidchallenge.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androidchallenge.data.Meditation
import com.example.androidchallenge.network.DataState
import com.example.androidchallenge.network.networkCall
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import retrofit2.Response

open class BaseViewModel : ViewModel() {

    val exceptionObserver = MutableLiveData<DataState.Exception>()
    val loadingObserver = MutableLiveData<DataState.Loading>()

}