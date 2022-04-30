package com.kalamou.weatherapplication

class WeatherApplicationTestApp: WeatherApplication() {
    override fun isInTest(): Boolean {
        return true
    }
}