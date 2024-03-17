package com.ltmb.fitness.scene.workoutselection

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.WorkoutSelectionUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutSelectionViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _workouts = MutableLiveData<List<WorkoutSelectionUiModel>>()
    val workouts: LiveData<List<WorkoutSelectionUiModel>> = _workouts


    init {
        val workouts = mutableListOf<WorkoutSelectionUiModel>()

        for (i in 1..10) {
            val workout = WorkoutSelectionUiModel(
                id = "$i",
                thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
                name = "Full Body Workout $i",
                duration = 120,
                selected = false
            )
            workouts.add(workout)
        }

        _workouts.value = workouts
    }
}