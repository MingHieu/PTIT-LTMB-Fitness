package com.ltmb.fitness.internal.injection.module

import android.content.Context
import com.ltmb.fitness.data.remote.api.AuthService
import com.ltmb.fitness.data.remote.api.UserService
import com.ltmb.fitness.internal.util.api.ApiKeyInterceptor
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val CLIENT_TIME_OUT = 120L

    @Provides
    fun provideBaseUrl(): String = "http://localhost:8080"

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        moshi: Moshi,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(apiKeyInterceptor)

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, moshi: Moshi, client: Lazy<OkHttpClient>): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .callFactory { client.get().newCall(it) }
            .build()

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}