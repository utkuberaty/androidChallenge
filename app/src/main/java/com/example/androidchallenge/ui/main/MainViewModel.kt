package com.example.androidchallenge.ui.main

import androidx.lifecycle.viewModelScope
import com.example.androidchallenge.base.BaseViewModel
import com.example.androidchallenge.network.DataState
import com.example.androidchallenge.network.repositories.MeditationRepository
import io.paperdb.Paper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {}