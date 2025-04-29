package com.example.fitmet.data

import android.app.Application
import android.content.Context


class FitApp : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}