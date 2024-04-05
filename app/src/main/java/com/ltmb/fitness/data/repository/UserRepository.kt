package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.remote.datasource.UserRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
) {

    suspend fun createNewUser(userId: String){
        remoteDataSource.createNewUser(userId)
    }
    suspend fun addGender(isGender: Boolean){
        remoteDataSource.addGender(isGender)
    }
}