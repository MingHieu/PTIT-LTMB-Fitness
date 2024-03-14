package com.ltmb.fitness.scene.createworkoutplan

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateWorkoutPlanViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {
}