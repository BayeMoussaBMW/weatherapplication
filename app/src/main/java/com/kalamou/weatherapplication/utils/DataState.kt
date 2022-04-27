package com.kalamou.weatherapplication.utils

sealed class DataState<out R> {

    companion object;

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}