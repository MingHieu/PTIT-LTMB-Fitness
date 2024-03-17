package com.ltmb.fitness.scene.getstarted

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetStartedViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {
    fun onClickLogin() {
        navigate(GetStartedFragmentDirections.toLogin())
    }

    fun onClickSignup() {
        navigate(GetStartedFragmentDirections.toSignup())
    }
}