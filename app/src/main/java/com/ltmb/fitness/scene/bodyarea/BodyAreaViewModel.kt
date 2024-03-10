package com.ltmb.fitness.scene.bodyarea

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BodyAreaViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _workoutPlans = MutableLiveData<List<WorkoutPlanUiModel>>()
    val workoutPlans: LiveData<List<WorkoutPlanUiModel>> = _workoutPlans

    init {
        val workoutPlansList = mutableListOf<WorkoutPlanUiModel>()

        for (i in 1..10) {
            val workoutPlan = WorkoutPlanUiModel(
                id = i.toLong(),
                thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
                name = "Full Body Workout $i",
                level = "Intermediate",
                duration = 60,
            )
            workoutPlansList.add(workoutPlan)
        }

        _workoutPlans.value = workoutPlansList
    }

    fun onWorkoutPlanItemClick() {
        navigate(BodyAreaFragmentDirections.toWorkoutPlanDetail())
    }
}