package com.ltmb.fitness.scene.bodyarea

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemWorkoutPlan2Binding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel

interface WorkoutPlanCallback {
    fun onItemClick(workoutPlanId: String)
}

class WorkoutPlanAdapter(private val workoutPlanCb: WorkoutPlanCallback) :
    BaseListAdapter<ItemWorkoutPlan2Binding, WorkoutPlanUiModel>() {

    override val layoutRes: Int get() = R.layout.item_workout_plan_2
    override fun bind(binding: ItemWorkoutPlan2Binding, item: WorkoutPlanUiModel) {
        binding.executeAfter {
            callback = workoutPlanCb
            workoutPlan = item
        }
    }

}