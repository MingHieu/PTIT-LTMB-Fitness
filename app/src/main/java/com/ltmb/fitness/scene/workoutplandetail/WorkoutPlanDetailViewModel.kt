package com.ltmb.fitness.scene.workoutplandetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.workoutplan.BookmarkWorkoutPlanModel
import com.ltmb.fitness.data.repository.WorkoutPlanRepository
import com.ltmb.fitness.uimodel.WorkoutPlanDetailUiModel
import com.ltmb.fitness.uimodel.WorkoutUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Collections
import javax.inject.Inject

@HiltViewModel
class WorkoutPlanDetailViewModel @Inject constructor(
    application: Application,
    private val workoutPlanRepository: WorkoutPlanRepository
) : BaseAndroidViewModel(application) {

    private val _workoutPlan = MutableLiveData<WorkoutPlanDetailUiModel>()
    val workoutPlan: LiveData<WorkoutPlanDetailUiModel> = _workoutPlan

    private val _workouts = MutableLiveData<List<WorkoutUiModel>>()
    val workouts: LiveData<List<WorkoutUiModel>> = _workouts

    val reloadData = MutableLiveData(false)

    fun getWorkoutPlanDetail(id: String) {
        viewModelScope.launch {
            setLoading(true)
            _workoutPlan.value = workoutPlanRepository.getWorkoutPlanDetail(id)
            _workouts.value = _workoutPlan.value?.workouts
            setLoading(false)
        }
    }

    fun swapItemInWorkoutList(currentIndex: Int, destinationIndex: Int) {
        val workouts = _workouts.value!!.toMutableList()
        Collections.swap(workouts, currentIndex, destinationIndex)
        _workouts.value = workouts
    }

    fun onStartClick() {
        workouts.value?.let { workoutsList ->
            navigate(
                WorkoutPlanDetailFragmentDirections.toWorkoutDetail(
                    workoutsList.toTypedArray(),
                    workoutPlan.value ?: WorkoutPlanDetailUiModel()
                )
            )
        }
    }

    fun onBookmarkClick() {
        viewModelScope.launch {
            setLoading(true)
            workoutPlan.value?.let {
                val model = BookmarkWorkoutPlanModel(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    thumbnail = it.thumbnail,
                    level = it.level,
                    duration = it.duration,
                    kcal = it.kcal,
                    workoutIds = it.workouts.map { workout -> workout.id }
                )
                val result = workoutPlanRepository.saveUserBookmarkWorkoutPlan(model)
                if (result) {
                    reloadData.value = true
                }
            }
            setLoading(false)
        }
    }

    fun onEditClick() {
        workoutPlan.value?.let {
            navigate(WorkoutPlanDetailFragmentDirections.toEdit(it))
        }
    }
}