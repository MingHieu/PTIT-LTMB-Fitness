package com.ltmb.fitness.data.remote.api

import com.ltmb.fitness.data.remote.model.user.UserDetailResponseModel
import retrofit2.http.GET

interface UserService {

    @GET(DETAIL)
    suspend fun fetchUserDetails(): UserDetailResponseModel

    companion object {
        const val DETAIL = "user/detail"
    }
}