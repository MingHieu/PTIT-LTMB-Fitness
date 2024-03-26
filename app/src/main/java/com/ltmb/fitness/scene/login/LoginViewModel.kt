package com.ltmb.fitness.scene.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.auth.LoginRequestModel
import com.ltmb.fitness.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository
) : BaseAndroidViewModel(application) {

    private var _email = ""
    private var _password = ""

    val isHandleLoginFailed = MutableLiveData(true)

    fun onClickLogin() {
//        navigate(LoginFragmentDirections.toSelectGender())
        println("Email: $_email, Password: $_password")
        viewModelScope.launch {
            setLoading(true)
            val result =
                authRepository.login(LoginRequestModel(email = _email, password = _password))
            if (result == null) {
                isHandleLoginFailed.value = false
            } else {
                navigate(LoginFragmentDirections.toHome())
            }
            setLoading(false)
        }
    }

    fun setEmail(text: String) {
        _email = text
    }

    fun setPassword(text: String) {
        _password = text
    }
}