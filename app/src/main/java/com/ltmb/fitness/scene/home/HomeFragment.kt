package com.ltmb.fitness.scene.home

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentHomeBinding
import com.ltmb.fitness.scene.workoutplan.WorkoutPlanAdapter
import com.ltmb.fitness.scene.workoutplan.WorkoutPlanCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutId get() = R.layout.fragment_home

    override fun initialize() {
        super.initialize()

        binding.workoutPlanAdapter = WorkoutPlanAdapter(object : WorkoutPlanCallback {
            override fun onItemClick() {
                viewModel.onWorkoutPlanClick()
            }

        })

        binding.bodyAreaAdapter = BodyAreaAdapter(object : BodyAreaCallback {
            override fun onItemClick() {
                viewModel.onBodyAreaItemClick()
            }
        })
    }
}