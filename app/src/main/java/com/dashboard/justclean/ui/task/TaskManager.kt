package com.dashboard.justclean.ui.task

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dashboard.justclean.data.NetworkHelper


class TaskManager(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val networkHelper = NetworkHelper(context)
    override fun doWork(): Result {
        if(networkHelper.isNetworkConnected()){

            return Result.success()

        }else{

            return Result.failure()

        }

    }

}