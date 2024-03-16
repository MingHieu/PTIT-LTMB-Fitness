package com.ltmb.fitness.uimodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.ltmb.fitness.base.ListAdapterItem
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutesAndSeconds
import java.io.Serializable

data class WorkoutSelectionUiModel(
    override val id: Long,
    val thumbnail: String,
    val name: String,
    val duration: Long,
    var selected: Boolean
) : ListAdapterItem, Serializable {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDurationInMinutesAndSeconds(): String = convertSecondsToMinutesAndSeconds(duration)
}