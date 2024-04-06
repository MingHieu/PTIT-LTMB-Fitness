package com.ltmb.fitness.data.repository

import com.google.firebase.auth.FirebaseUser
import com.ltmb.fitness.data.remote.datasource.AuthRemoteDataSource
import com.ltmb.fitness.data.remote.model.auth.LoginRequestModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource
) {

    suspend fun login(requestModel: LoginRequestModel): FirebaseUser? {
        return remoteDataSource.login(requestModel)
    }


    suspend fun register(requestModel: LoginRequestModel): FirebaseUser? {
        return remoteDataSource.register(requestModel)
    }

    suspend fun getCurrentUser() = remoteDataSource.getCurrentUser()

    suspend fun logout() = remoteDataSource.logout()
}