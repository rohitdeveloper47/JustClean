package com.dashboard.justclean

import android.app.Application
import com.dashboard.justclean.di.module.ApplicationModule
import com.dashboard.justclean.di.module.RepositoryModule
import com.dashboard.justclean.di.module.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JustClean : Application(){

    companion object{
        @get:Synchronized
        var instance = JustClean()
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JustClean)
            modules(listOf(ApplicationModule, RepositoryModule, ViewModelModule))
        }
    }

}