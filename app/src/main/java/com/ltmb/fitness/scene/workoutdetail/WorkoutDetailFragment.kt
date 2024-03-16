package com.ltmb.fitness.scene.workoutdetail

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutDetailFragment : BaseFragment<WorkoutDetailViewModel, FragmentWorkoutDetailBinding>() {
    override val layoutId get() = R.layout.fragment_workout_detail

}