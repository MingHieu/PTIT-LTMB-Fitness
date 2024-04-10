package com.ltmb.fitness.internal.injection.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.user.UserModel
import com.ltmb.fitness.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) : BaseAndroidViewModel(application) {

    var userModel = UserModel()

    fun updateUser() {
        viewModelScope.launch {
            userRepository.updateUser(userModel)
        }
    }
}