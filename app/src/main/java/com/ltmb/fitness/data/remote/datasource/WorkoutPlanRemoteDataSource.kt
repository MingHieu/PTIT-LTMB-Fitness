package com.ltmb.fitness.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.model.workout.WorkoutModel
import com.ltmb.fitness.data.remote.model.workoutplan.BookmarkWorkoutPlanModel
import com.ltmb.fitness.data.remote.model.workoutplan.WorkoutPlanDetailModel
import com.ltmb.fitness.data.remote.model.workoutplan.WorkoutPlanModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WorkoutPlanRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BaseRemoteDataSource() {

    private val collection = firestore.collection(FirestoreCollections.WORKOUT_PLAN)
    private val workoutCollection = firestore.collection(FirestoreCollections.WORKOUT)

    suspend fun searchWorkoutPlan(name: String, level: String) = invoke {
        val query = if (level.isNotBlank()) {
            collection.whereEqualTo("level", level)
        } else {
            collection
        }
        query
            .whereGreaterThanOrEqualTo("name", name)
            .whereLessThanOrEqualTo("name", "$name\uf7ff")
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(WorkoutPlanModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }

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
            .whereEqualTo("muscle", bodyAreaId)
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(WorkoutPlanModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }

    suspend fun getUserBookmarkWorkoutPlanList() = invoke {
        collection
            .whereEqualTo("userId", auth.currentUser?.uid)
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(WorkoutPlanModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }

    suspend fun saveUserBookmarkWorkoutPlan(model: BookmarkWorkoutPlanModel): DocumentReference =
        invoke {
            val data = hashMapOf(
                "name" to model.name,
                "description" to model.description,
                "thumbnail" to model.thumbnail,
                "level" to model.level,
                "duration" to model.duration,
                "kcal" to model.kcal,
                "workouts" to model.workoutIds.map { workoutCollection.document(it) }.toList(),
                "userId" to auth.currentUser?.uid
            )
            if (model.id.isBlank()) {
                collection.add(data).await()
            } else {
                collection.document(model.id).set(data).await()
                collection.document(model.id)
            }
        }

    suspend fun deleteUserBookmarkWorkoutPlanList(ids: List<String>): Void = invoke {
        val batch = firestore.batch()
        ids.forEach { id ->
            val reference = collection.document(id)
            batch.delete(reference)
        }
        batch.commit().await()
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
                }.orEmpty()

            WorkoutPlanDetailModel(
                id = workoutPlanId,
                name = data["name"] as? String ?: "",
                description = data["description"] as? String ?: "",
                thumbnail = data["thumbnail"] as? String ?: "",
                level = data["level"] as? String ?: "",
                duration = data["duration"] as? Long ?: 0,
                kcal = data["kcal"] as? Long ?: 0,
                bodyAreaId = data["bodyAreaId"] as? String ?: "",
                workouts = workouts,
                userId = data["userId"] as? String ?: ""
            )
        }
    }
}