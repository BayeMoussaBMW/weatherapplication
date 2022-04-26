package com.kalamou.weatherapplication

import android.app.Application
import com.kalamou.weatherapplication.db.AppDatabase

class WeatherApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}