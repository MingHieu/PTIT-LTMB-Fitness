package com.ltmb.fitness.data.remote.datasource


import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.FirestoreCollections
import com.ltmb.fitness.data.remote.UserCollections
import com.ltmb.fitness.data.remote.model.user.UserModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    db: FirebaseFirestore,
    private val auth: AuthRemoteDataSource
) : BaseRemoteDataSource() {

    private val collection = db.collection(FirestoreCollections.USER)

    suspend fun createNewUser(userId: String) = invoke {
        collection.document(userId)
            .set(hashMapOf<String, Any>())
    }

    suspend fun updateUser(userModel: UserModel) = invoke {
        auth.getCurrentUser()?.let {
            collection.document(it.uid)
                .set(userModel)
                .await()
        }
    }

}