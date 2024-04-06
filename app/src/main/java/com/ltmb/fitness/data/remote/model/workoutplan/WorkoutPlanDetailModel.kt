package com.ltmb.fitness.data.remote.model.workoutplan

import com.ltmb.fitness.data.remote.model.workout.WorkoutModel
import com.ltmb.fitness.uimodel.WorkoutPlanDetailUiModel

data class WorkoutPlanDetailModel(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var thumbnail: String = "",
    var level: String = "",
    var duration: Long = 0,
    var kcal: Long = 0,
    var bodyAreaId: String = "",
    var workouts: List<WorkoutModel> = listOf(),
    var userId: String = ""
) {

    fun toWorkoutPlanDetailUiModel() = WorkoutPlanDetailUiModel(
        id = id,
        name = name,
        description = description,
        thumbnail = thumbnail,
        workouts = workouts.map { it.toWorkoutUiModel() },
        duration = duration,
        kcal = kcal,
        level = level,
        userId = userId,
    )
}