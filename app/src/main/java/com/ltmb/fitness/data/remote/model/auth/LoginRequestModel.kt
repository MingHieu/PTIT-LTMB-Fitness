package com.ltmb.fitness.data.remote.model.auth

import com.ltmb.fitness.data.remote.BaseRequestModel

data class LoginRequestModel(
    val email: String,
    val password: String,
) : BaseRequestModel()
