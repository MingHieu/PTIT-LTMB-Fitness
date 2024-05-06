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
import com.ltmb.fitness.uimodel.GenderUiModel
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
    suspend fun getUser() = invoke{
        val document = auth.getCurrentUser()?.let {
            collection.document(it.uid)
                .get()
                .await()
        }
        UserModel(
            gender = GenderUiModel.valueOf(document?.data!!["gender"].toString()),
            age = (document.data!!["age"] as Long).toInt(),
            height = (document.data!!["height"] as Long).toInt(),
            weight = (document.data!!["weight"] as Long).toInt()
        )
    }

}