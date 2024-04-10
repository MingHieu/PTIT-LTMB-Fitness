package com.ltmb.fitness.scene.workoutplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutPlanBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutPlanFragment : BaseFragment<WorkoutPlanViewModel, FragmentWorkoutPlanBinding>() {

    override val layoutId get() = R.layout.fragment_workout_plan

    override fun initialize() {
        super.initialize()

        binding.searchBox.setValue(viewModel.keySearch.value.orEmpty())

        binding.searchBox.onTextChanged = {
            viewModel.keySearch.value = it
        }

        viewModel.keySearch.observeNonNull(viewLifecycleOwner) { searchQuery ->
            viewModel.workoutPlans.value?.let { list ->
                viewModel.workoutPlansSearch.value = if (searchQuery.isBlank()) {
                    list
                } else {
                    list.filter { it.name.lowercase().contains(searchQuery.lowercase()) }
                }
            }
        }

        binding.workoutPlanAdapter = WorkoutPlanAdapter(object : WorkoutPlanCallback {
            override fun onItemClick(workoutPlanId: String) {
                viewModel.onWorkoutPlanItemClick(workoutPlanId)
            }
        })
    }
}