package com.ltmb.fitness.scene.workoutplandetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.WorkoutPlanDetailUiModel
import com.ltmb.fitness.uimodel.WorkoutUiModel
import java.util.Collections
import javax.inject.Inject

class WorkoutPlanDetailViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _workoutPlan = MutableLiveData<WorkoutPlanDetailUiModel>()
    val workoutPlan: LiveData<WorkoutPlanDetailUiModel> = _workoutPlan

    private val _workouts = MutableLiveData<List<WorkoutUiModel>>()
    val workouts: LiveData<List<WorkoutUiModel>> = _workouts

    init {
        val workouts = mutableListOf<WorkoutUiModel>()

        for (i in 1..10) {
            val workout = WorkoutUiModel(
                id = "$i",
                thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
                name = "Full Body Workout $i",
                duration = 120,
            )
            workouts.add(workout)
        }

        _workoutPlan.value = WorkoutPlanDetailUiModel(
            id = "0",
            thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
            name = "Yoga & Mindfulness",
            description = "Find balance and tranquility with his yoga and mindfulness program. It focuses on flexibility, relaxation, and mental well-being.",
            workouts = workouts,
            duration = 0,
            kcal = 0,
        )

        _workouts.value = workouts
    }

    fun swapItemInWorkoutList(currentIndex: Int, destinationIndex: Int) {
        val workouts = _workouts.value!!.toMutableList()
        Collections.swap(workouts, currentIndex, destinationIndex)
        _workouts.value = workouts
    }

    fun onStartClick() {
        navigate(WorkoutPlanDetailFragmentDirections.toWorkoutDetail())
    }
}