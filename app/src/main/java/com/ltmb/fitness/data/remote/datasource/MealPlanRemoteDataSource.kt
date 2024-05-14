package com.ltmb.fitness.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.model.meal.MealModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MealPlanRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : BaseRemoteDataSource() {

    private val collection = firestore.collection(FirestoreCollections.MEAL_PLAN)

    suspend fun searchMealPlan(name: String, category: String) = invoke {
        val query = if (category.isNotBlank()) {
            collection.whereEqualTo("category", category)
        } else {
            collection
        }
        query
            .whereGreaterThanOrEqualTo("name", name)
            .whereLessThanOrEqualTo("name", "$name\uf7ff")
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(MealModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }

    suspend fun getMealPlanList(keySearch: String) = invoke {
        collection
            .whereGreaterThanOrEqualTo("name", keySearch)
            .whereLessThanOrEqualTo("name", "$keySearch\uf7ff")
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(MealModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }

    suspend fun filter(category: String) = invoke {
        collection
            .whereEqualTo("category", category)
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(MealModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }

    suspend fun filterFavorite() = invoke {
        collection
            .whereEqualTo("isFavorite", 1)
            .get().await()
            .documents
            .mapNotNull { document ->
                document.toObject(MealModel::class.java)?.apply {
                    id = document.id
                }
            }
            .toList()
    }

}