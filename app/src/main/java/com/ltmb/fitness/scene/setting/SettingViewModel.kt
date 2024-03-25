package com.ltmb.fitness.scene.setting

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) : BaseAndroidViewModel(application) {

    fun goToPersonalInfo() {
        navigate(SettingFragmentDirections.toPersonInfo())
    }

    fun logout() {
        navigate(SettingFragmentDirections.toLogin())
    }
}