package com.ltmb.fitness.data.remote.model.workout

import com.ltmb.fitness.uimodel.WorkoutUiModel

data class WorkoutModel(
    val id: String = "",
    val name: String = "",
    val thumbnail: String = "",
    val duration: Long = 0,
) {

    fun toWorkoutUiModel() =
        WorkoutUiModel(
            id = id,
            name = name,
            thumbnail = thumbnail,
            duration = duration
        )
}