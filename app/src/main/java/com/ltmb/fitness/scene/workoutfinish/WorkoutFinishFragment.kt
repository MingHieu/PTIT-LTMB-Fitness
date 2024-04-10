package com.ltmb.fitness.scene.workoutfinish

import androidx.navigation.fragment.navArgs
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutFinishBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutFinishFragment : BaseFragment<WorkoutFinishViewModel, FragmentWorkoutFinishBinding>() {

    override val layoutId get() = R.layout.fragment_workout_finish

    private val args by navArgs<WorkoutFinishFragmentArgs>()

    override fun initialize() {
        super.initialize()

        viewModel.createHistory(args.workoutPlanDetail, args.workouts, args.time, args.kcal)
    }
}