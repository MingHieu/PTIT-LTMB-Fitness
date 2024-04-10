package com.ltmb.fitness.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.model.workout.WorkoutModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WorkoutRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BaseRemoteDataSource() {

    private val collection = firestore.collection(FirestoreCollections.WORKOUT)

    suspend fun getWorkoutList(keySearch: String) = invoke {
        collection
            .whereGreaterThanOrEqualTo("name", keySearch)
            .whereLessThanOrEqualTo("name", "$keySearch\uf7ff")
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(WorkoutModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }
}