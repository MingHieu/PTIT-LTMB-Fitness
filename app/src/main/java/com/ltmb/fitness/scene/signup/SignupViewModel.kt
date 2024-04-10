package com.ltmb.fitness.scene.signup

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.auth.LoginRequestModel
import com.ltmb.fitness.data.repository.AuthRepository
import com.ltmb.fitness.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : BaseAndroidViewModel(application) {

    private var _email = ""
    private var _password = ""
    private var _confirmPassword = ""
    val isShowToast = MutableLiveData(false)
    var messageError = ""

    fun setEmail(text: String) {
        _email = text
    }

    fun setPassword(text: String) {
        _password = text
    }

    fun setConfirmPassword(text: String) {
        _confirmPassword = text
    }

    fun getEmail(): String {
        return this._email
    }

    fun getPassword(): String {
        return this._password
    }

    fun getConfirmPassword(): String {
        return this._confirmPassword
    }

    private fun validate(): Boolean {
        if( _password != _confirmPassword){
            messageError = "Retype password do not match!"
            isShowToast.value = true
            return false
        }
        return true
    }

    fun onClickLogin() {
        navigate(SignupFragmentDirections.toSignupLogin())
    }

    fun onClickSignup() {
        if (!validate()) {
            return
        }
        viewModelScope.launch {
            setLoading(true)
            try {
                val firebaseUser = authRepository.register(LoginRequestModel(_email, _password))
                if (firebaseUser != null) {
                    userRepository.createNewUser(firebaseUser.uid)
                }
                navigate(SignupFragmentDirections.toSelectGender())
            } catch (e: Exception) {
                println("------------------------Loi dang ky: $e")
                messageError = e.message.toString()
                isShowToast.value = true
            }
            setLoading(false)
        }
    }

}