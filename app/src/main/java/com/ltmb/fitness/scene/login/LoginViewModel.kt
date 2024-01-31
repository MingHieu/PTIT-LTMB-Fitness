package com.ltmb.fitness.scene.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.AuthRepository
import com.ltmb.fitness.internal.util.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository
) : BaseAndroidViewModel(application) {

    fun onLoginClick() {
        viewModelScope.launch {
            try {
                authRepository.login("admin", "123456aA")
            } catch (e: Failure) {
                println((e as Failure.UnknownError).exception)
            }
            navigate(LoginFragmentDirections.toHome())
        }
    }
}