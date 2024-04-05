package com.ltmb.fitness.data.remote.datasource


import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.UserCollections
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: AuthRemoteDataSource
) : BaseRemoteDataSource() {

    suspend fun createNewUser(userId: String) = invoke {
        db.collection(FirestoreCollections.USER)
            .document(userId)
            .set(hashMapOf<String, Any>())
    }

    suspend fun addGender(isGender: Boolean) = invoke {
        auth.getCurrentUser()?.let {
            db.collection(FirestoreCollections.USER)
                .document(it.uid)
                .set(hashMapOf(UserCollections.GENDER to isGender))
        }
    }
}