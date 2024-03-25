package com.ltmb.fitness.scene.workoutplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutPlanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutPlanFragment : BaseFragment<WorkoutPlanViewModel, FragmentWorkoutPlanBinding>() {

    override val layoutId get() = R.layout.fragment_workout_plan

    override fun initialize() {
        super.initialize()

        binding.searchBox.onTextChanged = { text ->
            viewModel.onSearch(text)
        }

        binding.workoutPlanAdapter = WorkoutPlanAdapter(object : WorkoutPlanCallback {
            override fun onItemClick(workoutPlanId: String) {
                viewModel.onWorkoutPlanItemClick(workoutPlanId)
            }
        })
    }
}