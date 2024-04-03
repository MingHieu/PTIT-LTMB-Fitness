package com.ltmb.fitness.scene.bodyarea

import androidx.navigation.fragment.navArgs
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentBodyAreaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BodyAreaFragment : BaseFragment<BodyAreaViewModel, FragmentBodyAreaBinding>() {
    override val layoutId get() = R.layout.fragment_body_area
    private val args by navArgs<BodyAreaFragmentArgs>()

    override fun initialize() {
        super.initialize()

        binding.actionBar.setTitle(args.bodyArea.name)

        viewModel.getWorkoutPlanList(args.bodyArea.id)

        binding.workoutPlanAdapter = WorkoutPlanAdapter(object : WorkoutPlanCallback {
            override fun onItemClick(workoutPlanId: String) {
                viewModel.onWorkoutPlanItemClick(workoutPlanId)
            }
        })
    }
}