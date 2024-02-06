package com.ltmb.fitness.data.remote.datasource

import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.api.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val service: UserService
) : BaseRemoteDataSource() {

    suspend fun fetchUserDetails() = invoke {
        service.fetchUserDetails()
    }
}