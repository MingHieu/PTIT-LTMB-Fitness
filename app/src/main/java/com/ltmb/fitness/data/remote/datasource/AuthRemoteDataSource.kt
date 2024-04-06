package com.ltmb.fitness.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.model.auth.LoginRequestModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val auth: FirebaseAuth
) : BaseRemoteDataSource() {

    suspend fun login(requestModel: LoginRequestModel) = invoke {
        auth
            .signInWithEmailAndPassword(requestModel.email, requestModel.password)
            .await()
            .user
    }

    suspend fun register(requestModel: LoginRequestModel) = invoke {
        auth
            .createUserWithEmailAndPassword(requestModel.email, requestModel.password)
            .await()
            .user
    }

    suspend fun getCurrentUser() = invoke {
        auth.currentUser
    }

    suspend fun logout() = invoke {
        auth.signOut()
    }
}

