package com.ltmb.fitness.data.remote.model.workout

import com.ltmb.fitness.uimodel.WorkoutUiModel

data class WorkoutModel(
    var id: String = "",
    val name: String = "",
    val thumbnail: String = "",
    val duration: Long = 0,
    val video: String = "",
    val tutorialText: String = ""
) {

    fun toWorkoutUiModel() =
        WorkoutUiModel(
            id = id,
            name = name,
            thumbnail = thumbnail,
            duration = duration,
            video = video,
            tutorialText = tutorialText
        )
}


