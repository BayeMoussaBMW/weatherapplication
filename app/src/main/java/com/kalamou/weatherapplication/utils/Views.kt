package com.kalamou.weatherapplication.utils

import android.view.View

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}


/**
 * Sets whether the view on which this method is called, is VISIBLE or GONE
 * @param visible true / false
 */
@Suppress("UNUSED")
fun View.visibleOrInvisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

fun View.enableOrDisable(enable: Boolean) {
    isEnabled = enable
}