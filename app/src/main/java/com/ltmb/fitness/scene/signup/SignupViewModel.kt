package com.ltmb.fitness.scene.signup

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.auth.LoginRequestModel
import com.ltmb.fitness.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository
) : BaseAndroidViewModel(application) {

    private var _email = ""
    private var _password = ""
    private var _confirmPassword = ""

    fun setEmail(text: String) {
        _email = text
    }

    fun setPassword(text: String) {
        _password = text
    }

    fun setConfirmPassword(text: String) {
        _confirmPassword = text
    }

    fun validate(): Boolean = true

    fun onClickLogin() {
        navigate(SignupFragmentDirections.toSignupLogin())
    }

    fun onClickSignup() {
        if (!validate()) {
            return
        }
        viewModelScope.launch {
            setLoading(true)
            authRepository.register(LoginRequestModel(_email, _password))
            setLoading(false)
            navigate(SignupFragmentDirections.toHome())
        }
    }

}