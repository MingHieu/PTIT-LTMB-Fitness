package com.ltmb.fitness.scene.createworkoutplan

import androidx.fragment.app.DialogFragment
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentCreateWorkoutPlanBinding
import com.ltmb.fitness.scene.workoutselection.WorkoutSelectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateWorkoutPlanFragment :
    BaseFragment<CreateWorkoutPlanViewModel, FragmentCreateWorkoutPlanBinding>() {
    override val layoutId get() = R.layout.fragment_create_workout_plan

    override fun initialize() {
        super.initialize()

        binding.addWorkoutButton.setOnClickListener {
            val workoutSelectionFragment = WorkoutSelectionFragment()
            workoutSelectionFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.TransparentDialog
            )
            workoutSelectionFragment.show(parentFragmentManager, workoutSelectionFragment.tag)
        }
    }

}