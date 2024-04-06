package com.ltmb.fitness.uimodel

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.ltmb.fitness.base.ListAdapterItem
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutes
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class WorkoutPlanDetailUiModel(
    override val id: String = "",
    val thumbnail: String = "",
    val name: String = "",
    val description: String = "",
    val workouts: List<WorkoutUiModel> = listOf(),
    val duration: Long = 0,
    val kcal: Long = 0,
    val level: String = "",
    val userId: String = ""
) : ListAdapterItem, Serializable, Parcelable {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDurationInMinutes(): String = convertSecondsToMinutes(duration)
}