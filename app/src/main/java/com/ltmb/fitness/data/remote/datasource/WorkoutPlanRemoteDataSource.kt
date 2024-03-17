package com.ltmb.fitness.data.remote.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.model.workoutplan.WorkoutPlanModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WorkoutPlanRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
) : BaseRemoteDataSource() {

    private val collection = firestore.collection(FirestoreCollections.WORKOUT_PLAN)

    suspend fun getWorkoutPlanList() = invoke {
        val result = mutableListOf<WorkoutPlanModel>()
        val querySnapshot = collection.get().await()
        for (document in querySnapshot.documents) {
            val model = document.toObject(WorkoutPlanModel::class.java)
            model?.let { result.add(it) }
        }
        result
    }

    suspend fun getWorkoutPlanListByBodyArea(bodyAreaId: String) = invoke {
        collection
            .whereEqualTo("bodyAreaId", bodyAreaId)
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(WorkoutPlanModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }
}