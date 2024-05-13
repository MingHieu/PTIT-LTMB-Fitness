package com.ltmb.fitness.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.model.mealdetail.MealDetailModel
import com.ltmb.fitness.data.remote.model.workout.WorkoutModel
import com.ltmb.fitness.data.remote.model.workoutplan.WorkoutPlanDetailModel
import com.ltmb.fitness.data.remote.model.workoutplan.WorkoutPlanModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MealPlanDetailRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BaseRemoteDataSource() {
    private val collection = firestore.collection(FirestoreCollections.MEAL_PLAN_DETAIL)

    suspend fun getMealPlanDetail(mealPlanId: String) = invoke {
        collection
            .whereEqualTo("id", mealPlanId)
            .get().await()
            .documents
            .mapNotNull { document ->
                val tmp = document.toObject(MealDetailModel::class.java)?.apply {
                    id = document.id
                }
                println("xyz $tmp")
                tmp
            }
            .firstOrNull()
    }
}