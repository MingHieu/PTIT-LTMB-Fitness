package com.ltmb.fitness.uimodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.ltmb.fitness.base.ListAdapterItem
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutesAndSeconds
import java.io.Serializable

data class WorkoutPlanUiModel(
    override val id: Long,
    val name: String,
    val thumbnail: String,
    val level: String,
    val description: String,
    val duration: Long,
    val kcal: Long,
    val workouts: Array<WorkoutUiModel>
) : ListAdapterItem, Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WorkoutPlanUiModel

        if (id != other.id) return false
        if (name != other.name) return false
        if (level != other.level) return false
        if (description != other.description) return false
        if (duration != other.duration) return false
        if (kcal != other.kcal) return false
        return workouts.contentEquals(other.workouts)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + level.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + kcal.hashCode()
        result = 31 * result + workouts.contentHashCode()
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSubTitle(): String = "${convertSecondsToMinutesAndSeconds(duration)} - $level"
}