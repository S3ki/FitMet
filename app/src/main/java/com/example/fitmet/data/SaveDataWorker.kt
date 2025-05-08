package com.example.fitmet.data

import StepCounter
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import java.time.LocalDate


class SaveDataWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    private val stepsRepo = StepsRepository(FitDatabase.getDatabase().stepsDao())
    private var counter = StepCounter(FitApp.appContext)


    private val sharedPref = FitApp.appContext.getSharedPreferences("fit_prefs", Context.MODE_PRIVATE)
    private val userId = sharedPref.getInt("current_user_id", -1)

    // Tallentaa askeleet requestin mukaan tietokantaan
    override suspend fun doWork(): Result {

        return try {
            Log.d("SVD1", "Worker triggered at ${System.currentTimeMillis()}")
            stepsRepo.insertSteps(Steps(0,userId, LocalDate.now().toString(), counter.steps().toInt()))
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

}
