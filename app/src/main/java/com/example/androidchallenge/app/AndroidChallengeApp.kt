package com.example.androidchallenge.app

import android.app.Application
import com.example.androidchallenge.di.*
import io.paperdb.Paper
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AndroidChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
        startKoin {
            androidContext(this@AndroidChallengeApp)
            androidLogger(Level.ERROR)
            modules(networkModule + repoModule + viewModelModule)
        }
    }
}