package com.kalamou.weatherapplication

import android.app.Application
import com.kalamou.weatherapplication.db.AppDatabase

open class WeatherApplication : Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
    }
    fun getVersionString(): String {
        // The version name defined in gradle
        return BuildConfig.VERSION_NAME

    }

    open fun isInTest(): Boolean {
        return false
    }
}