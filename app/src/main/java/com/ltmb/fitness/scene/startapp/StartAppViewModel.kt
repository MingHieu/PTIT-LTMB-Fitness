package com.ltmb.fitness.scene.startapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartAppViewModel @Inject constructor(
    application: Application,
) : BaseAndroidViewModel(application) {

}