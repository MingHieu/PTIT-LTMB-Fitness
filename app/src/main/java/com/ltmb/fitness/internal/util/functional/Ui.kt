package com.ltmb.fitness.internal.util.functional

import android.graphics.drawable.GradientDrawable
import android.view.View

fun setBackgroundRadius(view: View, radius: Float, color: Int = 0) {
    val shape = GradientDrawable()
    shape.cornerRadius = radius
    shape.setColor(color)

    view.background = shape
}