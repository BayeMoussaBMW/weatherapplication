package com.kalamou.weatherapplication.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kalamou.weatherapplication.model.*
import org.json.JSONObject

class SourceTypeConverters {
    @TypeConverter
    fun fromClouds(clouds: Clouds?): String {
        return JSONObject().apply {
            put("all", clouds?.all)
        }.toString()
    }

    @TypeConverter
    fun toClouds(clouds: String): Clouds {
        val json = JSONObject(clouds)
        return Clouds(json.get("all") as Int)
    }


    @TypeConverter
    fun fromCoord(coord: Coord): String {
        return JSONObject().apply {
            put("lat", coord.lat)
            put("lon", coord.lon)
        }.toString()
    }

    @TypeConverter
    fun toCoord(coord: String): Coord {
        val json = JSONObject(coord)
        return Coord(json.getDouble("lat"), json.getDouble("lon"))
    }

    @TypeConverter
    fun fromMain(main: Main?): String {
        return JSONObject().apply {
            put("feelsLike", main?.feelsLike)
            put("humidity", main?.humidity)
            put("pressure", main?.pressure)
            put("temp", main?.temp)
            put("tempMax", main?.tempMax)
            put("tempMin", main?.tempMin)
        }.toString()
    }

    @TypeConverter
    fun toMain(main: String): Main {
        val json = JSONObject(main)
        return Main(
            json.getDouble("feelsLike"),
            json.getInt("humidity"),
            json.getInt("pressure"),
            json.getDouble("temp"),
            json.getDouble("tempMax"),
            json.getDouble("tempMin")
        )
    }

    @TypeConverter
    fun fromSys(sys: Sys?): String {
        return JSONObject().apply {
            put("country", sys?.country)
            put("id", sys?.id)
            put("sunrise", sys?.sunrise)
            put("sunset", sys?.sunset)
            put("type", sys?.type)

        }.toString()
    }

    @TypeConverter
    fun toSys(sys: String): Sys {
        val json = JSONObject(sys)
        return Sys(
            json.getString("country"),
            json.getInt("id"),
            json.getInt("sunrise"),
            json.getInt("sunset"),
            json.getInt("type")
        )
    }

    @TypeConverter
    fun fromWeather(weatherList: List<Weather?>?): String {
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().toJson(weatherList, type)
    }

    @TypeConverter
    fun toWeather(weatherListString: String?): List<Weather>? {
        val type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson<List<Weather>>(weatherListString, type)
    }


    @TypeConverter
    fun fromWind(wind: Wind?): String {
        return JSONObject().apply {
            put("deg", wind?.deg)
            put("speed", wind?.speed)
        }.toString()
    }

    @TypeConverter
    fun toWind(wind: String): Wind {
        val json = JSONObject(wind)
        return Wind(
            json.getInt("deg"),
            json.getDouble("speed")
        )
    }

    @TypeConverter
    fun fromData(data: Data?): String {
        return JSONObject().apply {
            put("base", data?.base)
            put("clouds", data?.clouds)
            put("cod", data?.cod)
            put("coord", data?.coord)
            put("dt", data?.dt)
            put("id", data?.id)
            put("main", data?.main)
            put("name", data?.name)
            put("sys", data?.sys)
            put("timezone", data?.timezone)
            put("visibility", data?.visibility)
            put("weather", data?.weather)
            put("wind", data?.wind)

        }.toString()
    }

    @TypeConverter
    fun toData(wind: String): Data? {
        val json = JSONObject(wind)
        return Data(
            json.getString("base"),
            json.get("clouds") as Clouds?,
            json.getInt("cod"),
            json.get("coord") as Coord?,
            json.getInt("dt"),
            json.getInt("id"),
            json.get("main") as Main?,
            json.getString("name"),
            json.get("sys") as Sys?,
            json.getInt("timezone"),
            json.getInt("visibility"),
            json.get("weather") as List<Weather>?,
            json.get("wind") as Wind?,
        )
    }

}