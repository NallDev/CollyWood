package com.nalldev.collywood

import android.app.Application
import com.nalldev.collywood.di.databaseModule
import com.nalldev.collywood.di.networkModule
import com.nalldev.collywood.di.repositoryModule
import com.nalldev.collywood.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(databaseModule, networkModule, repositoryModule, viewModelModule))
        }
    }
}