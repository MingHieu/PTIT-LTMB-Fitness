package com.ltmb.fitness.scene.workoutplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutPlanBinding

class WorkoutPlanFragment : BaseFragment<WorkoutPlanViewModel, FragmentWorkoutPlanBinding>() {

    override val layoutId get() = R.layout.fragment_workout_plan

    override fun initialize() {
        super.initialize()

        binding.workoutPlanAdapter = WorkoutPlanAdapter(object : WorkoutPlanCallback {
            override fun onItemClick() {
                viewModel.onWorkoutPlanItemClick()
            }
        })
    }
}