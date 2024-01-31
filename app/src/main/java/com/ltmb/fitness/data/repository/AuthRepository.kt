package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.local.datasource.AuthLocalDataSource
import com.ltmb.fitness.data.remote.datasource.AuthRemoteDataSource
import com.ltmb.fitness.data.remote.model.auth.LoginRequestModel
import com.ltmb.fitness.internal.util.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val localDataSource: AuthLocalDataSource,
    private val remoteDataSource: AuthRemoteDataSource
) {

    suspend fun login(username: String, password: String) {
        try {
            val loginResponse = remoteDataSource.login(LoginRequestModel(username, password))
            localDataSource.insertToken(loginResponse.data.token)
        } catch (failure: Failure) {
            throw failure
        }
    }

    fun getAuthToken() = localDataSource.getToken()
}