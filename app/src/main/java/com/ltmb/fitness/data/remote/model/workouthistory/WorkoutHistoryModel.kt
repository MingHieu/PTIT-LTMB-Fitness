package com.ltmb.fitness.data.remote.model.workouthistory

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import java.util.Date

data class WorkoutHistoryModel(
    val workoutPlan: DocumentReference? = null,
    val createdAt: Date? = null,
    val workouts: Long = 0L,
    val times: Long = 0L,
    val kcal: Long = 0L,
    val userId: String = ""
)
