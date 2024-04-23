package com.ltmb.fitness.data.remote.model.workouthistory

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import java.util.Date

data class WorkoutHistoryModel(
    val workoutPlan: DocumentReference,
    val createdAt: Date,
    val workouts: Long,
    val times: Long,
    val kcal: Long,
    val userId: String
)
