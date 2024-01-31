package com.ltmb.fitness.scene.main

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application)