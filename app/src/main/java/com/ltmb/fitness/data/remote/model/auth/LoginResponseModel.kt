package com.ltmb.fitness.data.remote.model.auth

import com.ltmb.fitness.data.remote.BaseResponseModel
import com.squareup.moshi.Json

data class Data(
    @Json(name = "token") val token: String
) : BaseResponseModel()

data class LoginResponseModel(
    @Json(name = "message") val message: String,
    @Json(name = "data") val data: Data
) : BaseResponseModel()