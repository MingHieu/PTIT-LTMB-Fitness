package com.ltmb.fitness.scene.workoutplan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.WorkoutPlanRepository
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutPlanViewModel @Inject constructor(
    application: Application,
    private val workoutPlanRepository: WorkoutPlanRepository
) : BaseAndroidViewModel(application) {

    private val _workoutPlans = MutableLiveData<List<WorkoutPlanUiModel>>()
    val workoutPlans: LiveData<List<WorkoutPlanUiModel>> = _workoutPlans

    private var searchJob: Job? = null;

    init {
        viewModelScope.launch {
            _workoutPlans.value = workoutPlanRepository.getWorkoutPlanList()
        }
    }

    fun onSearch(keySearch: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            _workoutPlans.value = workoutPlanRepository.getWorkoutPlanList(keySearch)
        }
    }

    fun onWorkoutPlanItemClick(id: String) {
        navigate(WorkoutPlanFragmentDirections.toWorkoutPlanDetail(id))
    }
}