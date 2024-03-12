package com.ltmb.fitness.scene.workoutfinish

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.WorkoutPlanDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutFinishViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {
    private val _workoutPlan = MutableLiveData<WorkoutPlanDetailUiModel>()
    val workoutPlan: LiveData<WorkoutPlanDetailUiModel> = _workoutPlan

    fun onGoHomeClick() {
        navigate(WorkoutFinishFragmentDirections.toHome())
    }
}