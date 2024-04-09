package com.ltmb.fitness.scene.selectage

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectAgeViewModel @Inject constructor(application: Application) :
    BaseAndroidViewModel(application) {

    fun onClickContinue() {
        navigate(SelectAgeFragmentDirections.toSelectHeight())
    }

    fun onClickSkip(){
        navigate(SelectAgeFragmentDirections.toHome())
    }
}