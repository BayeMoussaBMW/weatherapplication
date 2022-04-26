package com.kalamou.weatherapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.kalamou.weatherapplication.R
import com.kalamou.weatherapplication.model.Data
import java.util.stream.IntStream.range

class BindingUtils {

    @BindingAdapter("state")
    fun ImageView.setWeatherImage(data: Data?) {
        data?.let {
            setImageResource(when (it.main.temp) {
                0.0 -> R.drawable.o13d

                else -> R.drawable.a01d
            })
        }
    }

}