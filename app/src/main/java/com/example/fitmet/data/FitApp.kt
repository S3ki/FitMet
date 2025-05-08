package com.example.fitmet.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class FitApp : Application() {
    companion object {
        lateinit var appContext: Context
        }

    override fun onCreate() {
        super.onCreate()
        Log.d("LISD", "OnCreate Context setup")
        appContext = applicationContext
        scheduleDailyWorker(appContext)
    }

    private fun scheduleDailyWorker(context: Context) {
        val request = PeriodicWorkRequestBuilder<SaveDataWorker>(24, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "SaveDataDaily",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )

    }

}