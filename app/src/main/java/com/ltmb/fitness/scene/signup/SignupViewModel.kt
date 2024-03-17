package com.ltmb.fitness.scene.signup

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {
    fun onClickLogin() {
        navigate(SignupFragmentDirections.toSignupLogin())
    }
}