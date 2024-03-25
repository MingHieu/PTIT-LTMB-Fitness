package com.ltmb.fitness.scene.bodyarea

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
class BodyAreaViewModel @Inject constructor(
    application: Application,
    private val workoutPlanRepository: WorkoutPlanRepository,
) : BaseAndroidViewModel(application) {

    private val _workoutPlans = MutableLiveData<List<WorkoutPlanUiModel>>()
    val workoutPlans: LiveData<List<WorkoutPlanUiModel>> = _workoutPlans

    fun onWorkoutPlanItemClick(workoutPlanId: String) {
        navigate(BodyAreaFragmentDirections.toWorkoutPlanDetail(workoutPlanId))
    }

    fun getWorkoutPlanList(bodyAreaId: String) {
        if (_workoutPlans.value != null) {
            return
        }
        viewModelScope.launch {
            setLoading(true)
            val workoutPlanList = workoutPlanRepository.getWorkoutPlanListByBodyAreaId(bodyAreaId)
            _workoutPlans.value = workoutPlanList
            setLoading(false)
        }
    }
}