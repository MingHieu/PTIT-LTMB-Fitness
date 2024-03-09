package com.ltmb.fitness.scene.workoutplan

import android.os.Bundle
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutPlanBinding

class WorkoutPlanFragment : BaseFragment<WorkoutPlanViewModel, FragmentWorkoutPlanBinding>() {

    override val layoutId get() = R.layout.fragment_workout_plan

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupScreen(getString(R.string.workout_plan_screen_title), true)
    }

    override fun initialize() {
        super.initialize()

        binding.workoutPlanAdapter = WorkoutPlanAdapter(object : WorkoutPlanCallback {
            override fun onItemClick() {
                viewModel.navigate(WorkoutPlanFragmentDirections.toWorkoutPlanDetailFragment())
            }
        })
    }
}