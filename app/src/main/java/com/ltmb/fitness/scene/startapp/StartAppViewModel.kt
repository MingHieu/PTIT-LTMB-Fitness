package com.ltmb.fitness.scene.startapp

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@HiltViewModel
class StartAppViewModel @Inject constructor(
    application: Application,
) : BaseAndroidViewModel(application) {

    init {
        viewModelScope.launch {
            delay(2000)
            navigate(StartAppFragmentDirections.toStartApp1())
        }
    }
}
