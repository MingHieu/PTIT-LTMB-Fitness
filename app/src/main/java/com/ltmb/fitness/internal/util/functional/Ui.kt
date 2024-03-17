package com.ltmb.fitness.internal.util.functional

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import androidx.annotation.AttrRes

fun setBackgroundRadius(view: View, radius: Float, color: Int = 0) {
    val shape = GradientDrawable()
    shape.cornerRadius = radius
    shape.setColor(color)

    view.background = shape
}

fun getColorInTheme(context: Context, @AttrRes resId: Int): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(resId, typedValue, true)
    return typedValue.data
}