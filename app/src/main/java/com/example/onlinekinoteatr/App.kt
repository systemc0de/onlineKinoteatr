package com.example.onlinekinoteatr

import android.app.Application
import com.example.onlinekinoteatr.di.appModule
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}