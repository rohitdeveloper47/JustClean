package com.dashboard.justclean

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import com.dashboard.justclean.di.module.ApplicationModule
import com.dashboard.justclean.di.module.RepositoryModule
import com.dashboard.justclean.di.module.ViewModelModule
import com.dashboard.justclean.ui.task.TaskManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JustClean : Application(){

    companion object{
        @get:Synchronized
        var instance = JustClean()
        val constraint: Constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val oneTimeWork : OneTimeWorkRequest = OneTimeWorkRequest.Builder(TaskManager::class.java)
            .setConstraints(constraint)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@JustClean)
            modules(listOf(ApplicationModule, RepositoryModule, ViewModelModule))
            //WorkManager.getInstance().enqueue(oneTimeWork)
        }
    }

}