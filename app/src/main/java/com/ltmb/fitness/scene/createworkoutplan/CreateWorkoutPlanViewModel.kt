package com.ltmb.fitness.scene.createworkoutplan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.workoutplan.BookmarkWorkoutPlanModel
import com.ltmb.fitness.data.repository.WorkoutPlanRepository
import com.ltmb.fitness.uimodel.WorkoutUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWorkoutPlanViewModel @Inject constructor(
    application: Application,
    private val workoutPlanRepository: WorkoutPlanRepository
) : BaseAndroidViewModel(application) {

    val model = BookmarkWorkoutPlanModel()

    private val _workouts = MutableLiveData<List<WorkoutUiModel>>()
    val workouts: LiveData<List<WorkoutUiModel>> = _workouts

    val reloadData = MutableLiveData(false)

    fun saveData() {
        viewModelScope.launch {
            setLoading(true)
            model.workoutIds.takeIf { it.isNotEmpty() }?.let { ids ->
                val workoutsMap = _workouts.value.orEmpty().associateBy { it.id }

                model.thumbnail = workoutsMap[ids.first()]?.thumbnail.orEmpty()

                val totalDuration = ids.mapNotNull { id -> workoutsMap[id]?.duration }.sum()
                model.duration = totalDuration

                val totalKcal = ids.mapNotNull { id -> workoutsMap[id]?.kcal }.sum()
                model.kcal = totalKcal
            }
            reloadData.value = workoutPlanRepository.saveUserBookmarkWorkoutPlan(model)
            setLoading(false)
        }
    }
}