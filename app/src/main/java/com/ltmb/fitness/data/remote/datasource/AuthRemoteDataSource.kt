package com.ltmb.fitness.data.remote.datasource

import com.ltmb.fitness.data.remote.api.AuthService
import com.ltmb.fitness.data.remote.BaseRemoteDataSource
import com.ltmb.fitness.data.remote.model.auth.LoginRequestModel
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val service: AuthService
) : BaseRemoteDataSource() {

    suspend fun login(requestModel: LoginRequestModel) = invoke {
        service.login(requestModel)
    }
}