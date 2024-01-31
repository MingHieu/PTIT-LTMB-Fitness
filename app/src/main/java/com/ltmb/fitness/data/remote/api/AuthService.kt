package com.ltmb.fitness.data.remote.api

import com.ltmb.fitness.data.remote.model.auth.LoginRequestModel
import com.ltmb.fitness.data.remote.model.auth.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST(LOGIN)
    suspend fun login(@Body requestModel: LoginRequestModel): LoginResponseModel

    companion object {
        const val LOGIN = "auth/signin"
    }
}