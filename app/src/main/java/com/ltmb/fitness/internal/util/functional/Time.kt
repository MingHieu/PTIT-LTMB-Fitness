package com.ltmb.fitness.internal.util.functional

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
fun convertSecondsToMinutesAndSeconds(seconds: Long): String {
    val time = LocalTime.MIN.plus(Duration.ofSeconds(seconds))

    val formattedMinutes = String.format("%02d", time.minute)
    val formattedSeconds = String.format("%02d", time.second)

    return "$formattedMinutes:$formattedSeconds"
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertSecondsToMinutes(seconds: Long): String {
    val time = LocalTime.MIN.plus(Duration.ofSeconds(seconds))

    return String.format("%d", time.minute)
}