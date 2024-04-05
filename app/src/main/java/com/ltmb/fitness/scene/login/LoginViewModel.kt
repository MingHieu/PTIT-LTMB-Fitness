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

    val isShowToast = MutableLiveData(false)
    var messageError = ""

    fun setEmail(text: String) {
        _email = text
    }

    fun setPassword(text: String) {
        _password = text
    }

    fun getEmail(): String {
        return this._email
    }
    fun getPassword():String{
        return this._password
    }

    fun onClickLogin() {
//        navigate(LoginFragmentDirections.toSelectGender())
        println("Email: $_email, Password: $_password")
        viewModelScope.launch {
            setLoading(true)
            try {
                authRepository.login(LoginRequestModel(email = _email, password = _password))
                navigate(LoginFragmentDirections.toHome())
            } catch (e: Exception) {
                println("-----------------Loi dang nhap: $e")
                messageError = e.message.toString()
                isShowToast.value = true
            }
            setLoading(false)
        }
    }

}