package com.ltmb.fitness.uimodel

import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutes

data class WorkoutHistoryUiModel(
    val workoutPlan: WorkoutPlanDetailUiModel,
    val workouts: Long,
    val times: Long,
    val kcal: Long,
) {

    fun getTimeInMinutes() = convertSecondsToMinutes(times)
}
