package com.ltmb.fitness.data.remote.model.workoutplan

import com.google.firebase.firestore.DocumentReference
import com.ltmb.fitness.uimodel.BookmarkWorkoutPlanUiModel
import com.ltmb.fitness.uimodel.ExerciseSearchUiModel
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel

data class WorkoutPlanModel(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var thumbnail: String = "",
    var level: String = "",
    var duration: Long = 0,
    var kcal: Long = 0,
    var muscle: String = "",
    var workouts: List<DocumentReference> = listOf(),
    var userId: String = ""
) {

    fun toWorkoutPlanUiModel(): WorkoutPlanUiModel =
        WorkoutPlanUiModel(
            id = id,
            thumbnail = thumbnail,
            name = name,
            level = level,
            duration = duration
        )

    fun toBookmarkWorkoutPlanUiModel(): BookmarkWorkoutPlanUiModel =
        BookmarkWorkoutPlanUiModel(
            id = id,
            thumbnail = thumbnail,
            name = name,
            level = level,
            duration = duration,
            selecting = false,
            selected = false
        )

    fun toSearchUiModel(): ExerciseSearchUiModel =
        ExerciseSearchUiModel(
            id = id,
            thumbnail = thumbnail,
            name = name,
            level = level,
            duration = duration
        )
}