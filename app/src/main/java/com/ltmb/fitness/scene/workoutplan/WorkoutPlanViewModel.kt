package com.ltmb.fitness.scene.workoutplan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import javax.inject.Inject

class WorkoutPlanViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _workoutPlans = MutableLiveData<List<WorkoutPlanUiModel>>()
    val workoutPlans: LiveData<List<WorkoutPlanUiModel>> = _workoutPlans

    init {
        val workoutPlansList = mutableListOf<WorkoutPlanUiModel>()

        for (i in 1..10) {
            val workoutPlan = WorkoutPlanUiModel(
                id = i.toLong(),
                name = "Full Body Workout $i",
                thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
                level = "Intermediate",
                description = "This workout plan targets all major muscle groups.",
                duration = 60,
                kcal = 300,
                workouts = arrayOf()
            )
            workoutPlansList.add(workoutPlan)
        }

        _workoutPlans.value = workoutPlansList
    }
}