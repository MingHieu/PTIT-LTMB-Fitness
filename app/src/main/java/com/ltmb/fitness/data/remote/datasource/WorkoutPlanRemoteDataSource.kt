package com.ltmb.fitness.data.remote.datasource

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.model.workout.WorkoutModel
import com.ltmb.fitness.data.remote.model.workoutplan.WorkoutPlanDetailModel
import com.ltmb.fitness.data.remote.model.workoutplan.WorkoutPlanModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WorkoutPlanRemoteDataSource @Inject constructor(
    firestore: FirebaseFirestore,
) : BaseRemoteDataSource() {

    private val collection = firestore.collection(FirestoreCollections.WORKOUT_PLAN)

    suspend fun getWorkoutPlanList(keySearch: String) = invoke {
        collection
            .whereGreaterThanOrEqualTo("name", keySearch)
            .whereLessThanOrEqualTo("name", "$keySearch\uf7ff")
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(WorkoutPlanModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
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

    suspend fun getWorkoutPlanDetail(workoutPlanId: String) = invoke {
        val workoutPlanDocument = collection.document(workoutPlanId).get().await()
        val workoutPlanData = workoutPlanDocument.data

        workoutPlanData?.let { data ->
            val workouts = (data["workouts"] as? List<DocumentReference>)
                ?.mapNotNull { workoutReference ->
                    workoutReference.get().await().toObject(WorkoutModel::class.java)?.apply {
                        id = workoutReference.id
                    }
                } ?: emptyList()

            WorkoutPlanDetailModel(
                id = workoutPlanId,
                name = data["name"] as? String ?: "",
                description = data["description"] as? String ?: "",
                thumbnail = data["thumbnail"] as? String ?: "",
                level = data["level"] as? String ?: "",
                duration = data["duration"] as? Long ?: 0,
                kcal = data["kcal"] as? Long ?: 0,
                bodyAreaId = data["bodyAreaId"] as? String ?: "",
                workouts = workouts
            )
        }
    }
}