package com.ltmb.fitness.scene.home

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    fun onSearchBoxClicked() {
        println("onSearchBoxClicked")
    }
}