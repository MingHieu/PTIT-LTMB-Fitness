package com.ltmb.fitness.scene.bodyarea

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentBodyAreaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BodyAreaFragment : BaseFragment<BodyAreaViewModel, FragmentBodyAreaBinding>() {
    override val layoutId get() = R.layout.fragment_body_area

    override fun initialize() {
        super.initialize()

        binding.workoutPlanAdapter = WorkoutPlanAdapter(object : WorkoutPlanCallback {
            override fun onItemClick() {
                viewModel.onWorkoutPlanItemClick()
            }
        })
    }
}