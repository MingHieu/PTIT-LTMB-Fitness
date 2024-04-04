package com.ltmb.fitness.scene.createworkoutplan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.workoutplan.BookmarkWorkoutPlanModel
import com.ltmb.fitness.data.repository.WorkoutPlanRepository
import com.ltmb.fitness.uimodel.WorkoutSelectionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWorkoutPlanViewModel @Inject constructor(
    application: Application,
    private val workoutPlanRepository: WorkoutPlanRepository
) : BaseAndroidViewModel(application) {

    val model = BookmarkWorkoutPlanModel()

    private val _workouts = MutableLiveData<List<WorkoutSelectionUiModel>>()
    val workouts: LiveData<List<WorkoutSelectionUiModel>> = _workouts

    val workoutsSelected = MutableLiveData<List<WorkoutSelectionUiModel>>()

    val reloadData = MutableLiveData(false)

    val toastText = MutableLiveData("")

    init {
        val workouts = mutableListOf<WorkoutSelectionUiModel>()

        for (i in 1..10) {
            val workout = WorkoutSelectionUiModel(
                id = "$i",
                thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
                name = "Full Body Workout $i",
                duration = 120,
                kcal = 100,
                selected = false
            )
            workouts.add(workout)
        }

        _workouts.value = workouts
    }

    private fun validate(): Boolean {
        if (model.name.isBlank()) {
            toastText.value = "Name is required"
            return false
        }

        if (workoutsSelected.value.isNullOrEmpty()) {
            toastText.value = "Workouts is required"
            return false
        }

        return true
    }

    fun saveData() {
        if (!validate()) {
            return
        }

        viewModelScope.launch {
            setLoading(true)
            model.workoutIds = workoutsSelected.value?.map { it.id }.orEmpty()
            model.workoutIds.takeIf { it.isNotEmpty() }?.let { ids ->
                val workoutsMap = _workouts.value.orEmpty().associateBy { it.id }

                model.thumbnail = workoutsMap[ids.first()]?.thumbnail.orEmpty()

                val totalDuration = ids.mapNotNull { id -> workoutsMap[id]?.duration }.sum()
                model.duration = totalDuration

                val totalKcal = ids.mapNotNull { id -> workoutsMap[id]?.kcal }.sum()
                model.kcal = totalKcal
            }
            reloadData.value = workoutPlanRepository.saveUserBookmarkWorkoutPlan(model)
            if (reloadData.value == false) {
                toastText.value = "Something went wrong"
            }
            setLoading(false)
        }
    }

    fun onItemSelectedChanged(id: String, isSelected: Boolean) {
        _workouts.value = _workouts.value?.map { workout ->
            if (workout.id == id) {
                workout.selected = isSelected
            }
            workout
        }
    }
}