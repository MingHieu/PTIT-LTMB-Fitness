package com.ltmb.fitness.scene.startapp3

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartApp3ViewModel @Inject constructor(
    application: Application,
) : BaseAndroidViewModel(application) {
    fun onClickContinue() {
        navigate(StartApp3FragmentDirections.toAuth())
    }
}