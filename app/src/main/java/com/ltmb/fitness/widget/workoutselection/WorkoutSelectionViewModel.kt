package com.ltmb.fitness.widget.workoutselection

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.WorkoutSelectionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutSelectionViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    val keySearch = MutableLiveData("")

    val workouts = MutableLiveData<List<WorkoutSelectionUiModel>>()

    val workoutsSearch = MutableLiveData<List<WorkoutSelectionUiModel>>()
}