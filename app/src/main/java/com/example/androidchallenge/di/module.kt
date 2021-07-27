package com.example.androidchallenge.di

import com.example.androidchallenge.network.MeditationApi
import com.example.androidchallenge.network.repositories.MeditationRepository
import com.example.androidchallenge.ui.home.HomeViewModel
import com.example.androidchallenge.ui.login.LoginViewModel
import com.example.androidchallenge.ui.main.MainViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .writeTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .connectTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonblob.com/api/jsonBlob/")
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        retrofit.create(MeditationApi::class.java)
    }
}

val repoModule = module {
    single { MeditationRepository(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}