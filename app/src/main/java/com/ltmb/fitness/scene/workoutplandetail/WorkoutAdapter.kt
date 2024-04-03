package com.ltmb.fitness.scene.workoutplandetail

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemWorkoutDraggableBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.WorkoutUiModel

class WorkoutAdapter :
    BaseListAdapter<ItemWorkoutDraggableBinding, WorkoutUiModel>() {

    override val layoutRes: Int get() = R.layout.item_workout_draggable
    override fun bind(binding: ItemWorkoutDraggableBinding, item: WorkoutUiModel) {
        binding.executeAfter {
            workout = item
        }
    }

}