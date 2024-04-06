package com.ltmb.fitness.data.remote.model.workout

import com.ltmb.fitness.uimodel.WorkoutSelectionUiModel
import com.ltmb.fitness.uimodel.WorkoutUiModel

data class WorkoutModel(
    var id: String = "",
    val name: String = "",
    val thumbnail: String = "",
    val duration: Long = 0,
    val kcal: Long = 0,
    val video: String = "",
    val muscle: String = "",
    val level: String = "",
    val equipment: String = "",
    val type: String = "",
    val instructions: String = ""
) {

    fun toWorkoutUiModel() =
        WorkoutUiModel(
            id = id,
            name = name,
            thumbnail = thumbnail,
            duration = duration,
            kcal = kcal,
            video = video,
            tutorialText = instructions
        )

    fun toWorkoutSelectionUiModel() =
        WorkoutSelectionUiModel(
            id = id,
            name = name,
            thumbnail = thumbnail,
            duration = duration,
            kcal = kcal,
            level = level,
            selected = false
        )
}


