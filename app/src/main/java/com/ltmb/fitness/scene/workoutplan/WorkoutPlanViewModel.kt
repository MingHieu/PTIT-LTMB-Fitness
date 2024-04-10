package com.ltmb.fitness.scene.workoutplan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.WorkoutPlanRepository
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutPlanViewModel @Inject constructor(
    application: Application,
    private val workoutPlanRepository: WorkoutPlanRepository
) : BaseAndroidViewModel(application) {

    private val _workoutPlans = MutableLiveData<List<WorkoutPlanUiModel>>()
    val workoutPlans: LiveData<List<WorkoutPlanUiModel>> = _workoutPlans

    val workoutPlansSearch = MutableLiveData<List<WorkoutPlanUiModel>>()

    val keySearch = MutableLiveData("")

    init {
        viewModelScope.launch {
            val list = workoutPlanRepository.getWorkoutPlanList()
            _workoutPlans.value = list
            workoutPlansSearch.value = list
        }
    }

    fun onWorkoutPlanItemClick(id: String) {
        navigate(WorkoutPlanFragmentDirections.toWorkoutPlanDetail(id))
    }
}