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
        try {
            val result = remoteDataSource.login(requestModel)
            println(result)
            return result
        } catch (e: Exception) {
            println("Login failed: $e")
        }
        return null
    }

    suspend fun register(requestModel: LoginRequestModel) {
        try {
            val result = remoteDataSource.register(requestModel)
            println(result)
        } catch (e: Exception) {
            println("Sign up failed: $e")
        }
    }

    suspend fun getCurrentUser() = remoteDataSource.getCurrentUser()

    suspend fun logout() = remoteDataSource.logout()
}