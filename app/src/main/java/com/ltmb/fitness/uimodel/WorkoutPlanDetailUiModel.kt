package com.ltmb.fitness.uimodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.ltmb.fitness.base.ListAdapterItem
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutes
import java.io.Serializable

data class WorkoutPlanDetailUiModel(
    override val id: Long,
    val thumbnail: String,
    val name: String,
    val description: String,
    val workouts: List<WorkoutUiModel>,
    val duration: Long,
    val kcal: Long,
) : ListAdapterItem, Serializable {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDurationInMinutes(): String = convertSecondsToMinutes(duration)

    fun getWorkoutsNumber(): Int = workouts.size
}