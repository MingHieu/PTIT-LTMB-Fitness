package com.ltmb.fitness.scene.workoutselection

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseBottomSheetDialogFragment
import com.ltmb.fitness.databinding.FragmentWorkoutSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutSelectionFragment :
    BaseBottomSheetDialogFragment<WorkoutSelectionViewModel, FragmentWorkoutSelectionBinding>() {
    override val layoutId get() = R.layout.fragment_workout_selection

    override fun initialize() {
        super.initialize()

        binding.adapter = WorkoutSelectionAdapter(object : WorkoutCallback {
            override fun onItemClick() {
                TODO("Not yet implemented")
            }

            override fun onItemSelectedChanged(id: String, isSelected: Boolean) {
                TODO("Not yet implemented")
            }
        })
    }

}