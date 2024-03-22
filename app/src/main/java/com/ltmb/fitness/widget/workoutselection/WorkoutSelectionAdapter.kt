package com.ltmb.fitness.widget.workoutselection

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemWorkoutSelectionBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.WorkoutSelectionUiModel

interface WorkoutCallback {
    fun onItemClick()

    fun onItemSelectedChanged(id: String, isSelected: Boolean)
}

class WorkoutSelectionAdapter(private val workoutCb: WorkoutCallback) :
    BaseListAdapter<ItemWorkoutSelectionBinding, WorkoutSelectionUiModel>() {

    override val layoutRes: Int get() = R.layout.item_workout_selection
    override fun bind(binding: ItemWorkoutSelectionBinding, item: WorkoutSelectionUiModel) {
        binding.executeAfter {
            callback = workoutCb
            workoutSelection = item
        }
    }

}