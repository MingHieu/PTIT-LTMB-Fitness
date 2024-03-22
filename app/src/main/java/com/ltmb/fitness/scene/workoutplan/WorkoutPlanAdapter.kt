package com.ltmb.fitness.scene.workoutplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemWorkoutPlanBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel

interface WorkoutPlanCallback {
    fun onItemClick(workoutPlanId: String)
}

class WorkoutPlanAdapter(private val workoutPlanCb: WorkoutPlanCallback) :
    BaseListAdapter<ItemWorkoutPlanBinding, WorkoutPlanUiModel>() {

    override val layoutRes: Int get() = R.layout.item_workout_plan
    override fun bind(binding: ItemWorkoutPlanBinding, item: WorkoutPlanUiModel) {
        binding.executeAfter {
            callback = workoutPlanCb
            workoutPlan = item
        }
    }

}