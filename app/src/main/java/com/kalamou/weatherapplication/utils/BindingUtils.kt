package com.kalamou.weatherapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.kalamou.weatherapplication.R
import com.kalamou.weatherapplication.model.Data


@BindingAdapter("state")
fun ImageView.setWeatherImage(data: Data?) {

    data.let {
        setImageResource(
            when (it?.weather?.get(3)?.icon) {
                "01d" -> R.drawable.a01d
                "01n" -> R.drawable.b01n
                "02d" -> R.drawable.c02d
                "02n" -> R.drawable.d02n
                "03d" -> R.drawable.e03d
                "03n" -> R.drawable.f03n
                "04d" -> R.drawable.g04d
                "04n" -> R.drawable.h04n
                "09d" -> R.drawable.i09d
                "09n" -> R.drawable.j09n
                "10d" -> R.drawable.k10d
                "10n" -> R.drawable.l10n
                "11d" -> R.drawable.m11d
                "11n" -> R.drawable.n11n
                "13d" -> R.drawable.o13d
                "13n" -> R.drawable.p13n
                "50d" -> R.drawable.q50d
                "50n" -> R.drawable.u50n
                else -> R.drawable.c02d
            }
        )
    }
}

