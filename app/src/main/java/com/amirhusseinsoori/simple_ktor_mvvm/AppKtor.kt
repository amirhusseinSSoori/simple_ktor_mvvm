package com.amirhusseinsoori.simple_ktor_mvvm

import android.app.Application
import com.amirhusseinsoori.simple_ktor_mvvm.data.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class AppKtor:Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AppKtor)
            modules(listOf(AppModule))
        }
    }
}