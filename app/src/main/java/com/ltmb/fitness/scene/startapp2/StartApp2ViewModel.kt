package com.ltmb.fitness.scene.startapp1

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartApp2ViewModel @Inject constructor(
    application: Application,
) : BaseAndroidViewModel(application) {
    fun onClickContinue(){
        navigate(StartApp2FragmentDirections.actionStartApp2FragmentToStartApp3Fragment2())
    }
}