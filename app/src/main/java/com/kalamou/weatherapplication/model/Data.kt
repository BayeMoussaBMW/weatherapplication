package com.kalamou.weatherapplication.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Entity(tableName = "data")
@Serializable
data class Data(
    @ColumnInfo
    @SerialName("base")
    val base: String,
    @ColumnInfo
    @SerialName("clouds")
    val clouds: Clouds?,
    @ColumnInfo
    @SerialName("cod")
    val cod: Int,
    @ColumnInfo
    @SerialName("coord")
    val coord: Coord?,
    @ColumnInfo
    @SerialName("dt")
    val dt: Int,
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int,
    @ColumnInfo
    @SerialName("main")
    val main: Main?,
    @ColumnInfo
    @SerialName("name")
    val name: String,
    @ColumnInfo
    @SerialName("sys")
    val sys: Sys?,
    @ColumnInfo
    @SerialName("timezone")
    val timezone: Int,
    @ColumnInfo
    @SerialName("visibility")
    val visibility: Int,
    @ColumnInfo
    @SerialName("weather")
    val weather: List<Weather>?,
    @ColumnInfo
    @SerialName("wind")
    val wind: Wind?
) {
    constructor(): this("", null, 0, null, 0, 0, null, "", null, 0, 0, emptyList(), null)
}