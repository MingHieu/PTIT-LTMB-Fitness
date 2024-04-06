package com.ltmb.fitness.widget.workoutselection

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseBottomSheetDialogFragment
import com.ltmb.fitness.databinding.FragmentWorkoutSelectionBinding
import com.ltmb.fitness.uimodel.WorkoutSelectionUiModel
import dagger.hilt.android.AndroidEntryPoint

interface ItemSelectedChangeListener {
    fun onChanged(id: String, isSelected: Boolean)
}

@AndroidEntryPoint
class WorkoutSelectionFragment(
    private val workoutList: List<WorkoutSelectionUiModel>,
) : BaseBottomSheetDialogFragment<WorkoutSelectionViewModel, FragmentWorkoutSelectionBinding>() {

    override val layoutId get() = R.layout.fragment_workout_selection

    var itemSelectedChangeListener: ItemSelectedChangeListener? = null

    override fun initialize() {
        super.initialize()

        viewModel.workouts.value = workoutList

        binding.adapter = WorkoutSelectionAdapter(object : WorkoutCallback {
            override fun onItemClick() {

            }

            override fun onItemSelectedChanged(id: String, isSelected: Boolean) {
                itemSelectedChangeListener?.onChanged(id, isSelected)
            }
        })
    }

    fun setItemSelectedChangedListener(listener: ItemSelectedChangeListener) {
        itemSelectedChangeListener = listener
    }

}