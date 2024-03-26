package com.ltmb.fitness.internal.util.api

import com.ltmb.fitness.data.repository.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor(
    private val authRepository: AuthRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
//        val authToken = authRepository.getAuthToken()
//        if (authToken != null) {
//            builder.header("Authorization", authToken)
//        }
        return chain.proceed(builder.build())
    }
}
