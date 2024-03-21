package com.ltmb.fitness.scene.workoutdetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.TutorialType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    val tutorialType = MutableLiveData(TutorialType.TEXT)

    fun onSkipClick() {
        navigate(WorkoutDetailFragmentDirections.toWorkoutFinish())
    }

    fun setTutorialType(type: TutorialType) {
        tutorialType.value = type
    }
}