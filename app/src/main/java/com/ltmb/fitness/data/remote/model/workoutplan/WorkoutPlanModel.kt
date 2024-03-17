package com.ltmb.fitness.data.remote.model.workoutplan

import com.google.firebase.firestore.DocumentReference
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel

data class WorkoutPlanModel(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var thumbnail: String = "",
    var level: String = "",
    var duration: Long = 0,
    var kcal: Long = 0,
    var bodyAreaId: String = "",
    var workouts: List<DocumentReference> = listOf()
) {

    fun toWorkoutPlanUiModel(): WorkoutPlanUiModel =
        WorkoutPlanUiModel(
            id = id,
            thumbnail = thumbnail,
            name = name,
            level = level,
            duration = duration
        )
}