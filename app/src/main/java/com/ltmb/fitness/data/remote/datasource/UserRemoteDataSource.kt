package com.ltmb.fitness.data.remote.datasource

import com.ltmb.fitness.data.remote.api.UserService
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val service: UserService
) : BaseRemoteDataSource() {

    suspend fun fetchUserDetails() = invoke {
        service.fetchUserDetails()
    }
}