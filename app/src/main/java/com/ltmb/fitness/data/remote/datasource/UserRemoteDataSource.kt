package com.ltmb.fitness.data.remote.datasource

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.api.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val service: UserService,
    private val db: FirebaseFirestore = Firebase.firestore
) : BaseRemoteDataSource() {

    suspend fun fetchUserDetails() = invoke {
        service.fetchUserDetails()
    }

    suspend fun createNewUser(userId: String) = invoke {
        // Create a new user with a first and last name
        val user = hashMapOf(
            "firstName" to "Ada",
            "lastName" to "Lovelace",
            "gender" to false,
            "age" to 18,
            "height" to 170,
            "weight" to 60
        )

        db.collection(FirestoreCollections.USER)
            .document(userId)
            .set(user)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added with ID: $userId")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

}