package com.ltmb.fitness.data.local.datasource

import android.content.SharedPreferences
import com.ltmb.fitness.data.local.delegate.StringPreference
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    private var authPrefs by StringPreference(sharedPreferences, PREF_AUTH)

    fun insertToken(token: String?) {
        authPrefs = token
    }

    fun getToken(): String? = authPrefs

    companion object {
        private const val PREF_AUTH = "auth_token"
    }
}
