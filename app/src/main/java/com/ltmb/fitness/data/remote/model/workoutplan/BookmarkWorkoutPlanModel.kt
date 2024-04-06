package com.ltmb.fitness.data.remote.model.workoutplan

data class BookmarkWorkoutPlanModel(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var thumbnail: String = "",
    var level: String = "Beginner",
    var duration: Long = 0,
    var kcal: Long = 0,
    var workoutIds: List<String> = listOf()
)