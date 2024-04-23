package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.remote.datasource.UserRemoteDataSource
import com.ltmb.fitness.data.remote.model.user.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource
) {

    suspend fun createNewUser(userId: String){
        remoteDataSource.createNewUser(userId)
    }

    suspend fun updateUser(userModel: UserModel){
        remoteDataSource.updateUser(userModel)
    }

    suspend fun getUser(): UserModel {
        return remoteDataSource.getUser()
    }

}