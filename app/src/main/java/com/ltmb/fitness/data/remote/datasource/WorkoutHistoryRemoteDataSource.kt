package com.ltmb.fitness.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.model.workouthistory.WorkoutHistoryModel
import com.ltmb.fitness.uimodel.WorkoutHistoryUiModel
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class WorkoutHistoryRemoteDataSource @Inject constructor(
    firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BaseRemoteDataSource() {

    private val collection = firestore.collection(FirestoreCollections.WORKOUT_HISTORY)
    private val workoutPlanCollection = firestore.collection(FirestoreCollections.WORKOUT_PLAN)

    suspend fun saveHistory(workoutHistoryUiModel: WorkoutHistoryUiModel) = invoke {
        collection.add(
            WorkoutHistoryModel(
                workoutPlan = workoutPlanCollection.document(workoutHistoryUiModel.workoutPlan.id),
                createdAt = Date(),
                workouts = workoutHistoryUiModel.workouts,
                times = workoutHistoryUiModel.times,
                kcal = workoutHistoryUiModel.kcal,
                userId = auth.currentUser?.uid.orEmpty()
            )
        ).await()
    }
}