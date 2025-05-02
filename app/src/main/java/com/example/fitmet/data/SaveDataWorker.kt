package com.example.fitmet.data

import StepCounter
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters


class SaveDataWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    private val stepsRepo = StepsRepository(FitDatabase.getDatabase().stepsDao())
    private var counter = StepCounter(FitApp.appContext)

    override suspend fun doWork(): Result {

        return try {
            Log.d("WRK", "OnCreate WorkRequest")
            stepsRepo.insertSteps(Steps(0,1,"02.05.2025", 23))
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

    private suspend fun saveToDatabase() {
        // Access your repository or DAO to save the data
        // Example: myDao.insert(data)
    }
}
