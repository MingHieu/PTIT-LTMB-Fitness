package com.ltmb.fitness.data.remote.model.user

import com.ltmb.fitness.data.remote.BaseResponseModel
import com.ltmb.fitness.uimodel.UserDetailUiModel
import com.squareup.moshi.Json

data class UserDetailResponseModel(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "username") val username: String
) : BaseResponseModel() {

    fun toUiModel() = UserDetailUiModel(
        id = id,
        name = name,
        username = username
    )
}