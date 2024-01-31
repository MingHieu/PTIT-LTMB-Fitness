package com.ltmb.fitness.data.remote.model.auth

import com.ltmb.fitness.data.remote.BaseRequestModel
import com.squareup.moshi.Json

data class LoginRequestModel(
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
) : BaseRequestModel()
