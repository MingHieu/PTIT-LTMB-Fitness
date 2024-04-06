package com.ltmb.fitness.scene.workoutfinish

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.WorkoutHistoryRepository
import com.ltmb.fitness.uimodel.WorkoutHistoryUiModel
import com.ltmb.fitness.uimodel.WorkoutPlanDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutFinishViewModel @Inject constructor(
    application: Application,
    private val workoutHistoryRepository: WorkoutHistoryRepository
) : BaseAndroidViewModel(application) {

    private val _history = MutableLiveData<WorkoutHistoryUiModel>()
    val history: LiveData<WorkoutHistoryUiModel> = _history

    fun createHistory(
        workoutPlan: WorkoutPlanDetailUiModel,
        workouts: Long,
        times: Long,
        kcal: Long
    ) {
        viewModelScope.launch {
            val model = WorkoutHistoryUiModel(
                workoutPlan = workoutPlan,
                workouts = workouts,
                times = times,
                kcal = kcal
            )
            _history.value = model
            workoutHistoryRepository.saveHistory(model)
        }
    }

    fun onGoHomeClick() {
        navigate(WorkoutFinishFragmentDirections.toHome())
    }

    fun onViewReportClick() {
        navigate(WorkoutFinishFragmentDirections.toReport())
    }
}

