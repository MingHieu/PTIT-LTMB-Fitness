package com.ltmb.fitness.scene.workoutpreferences

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutPreferencesBinding

class WorkoutPreferencesFragment :
    BaseFragment<WorkoutPreferencesViewModel, FragmentWorkoutPreferencesBinding>() {
    override val layoutId get() = R.layout.fragment_workout_preferences
}