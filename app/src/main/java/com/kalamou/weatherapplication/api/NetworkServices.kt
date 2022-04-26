package com.kalamou.weatherapplication.api

import com.google.gson.JsonObject
import com.kalamou.weatherapplication.model.Data
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NetworkServices {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService = retrofit.create(WeatherService::class.java)

    suspend fun proceedGetDataByCity(name: String): Response<Data> {
        return weatherService.getData(name)
    }
}

interface WeatherService {
    @GET("weather?&&units=metric&appid=b20518632c2b6b495064f55c456224ce")
    suspend fun getData(@Query("q") name: String): Response<Data>
}