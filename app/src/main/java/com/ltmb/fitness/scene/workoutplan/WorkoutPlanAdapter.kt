package com.ltmb.fitness.scene.workoutplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemWorkoutPlanBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import com.squareup.picasso.Picasso

interface WorkoutPlanCallback {
    fun onItemClick()
}

class WorkoutPlanAdapter(private val workoutPlanCb: WorkoutPlanCallback) :
    BaseListAdapter<ItemWorkoutPlanBinding, WorkoutPlanUiModel>() {

    override val layoutRes: Int get() = R.layout.item_workout_plan
    override fun bind(binding: ItemWorkoutPlanBinding, item: WorkoutPlanUiModel) {
        binding.executeAfter {
            Picasso.get()
                .load(item.thumbnail)
                .placeholder(R.drawable.animation_skeleton)
                .error(R.drawable.img_exercise_sample)
                .into(thumbnail)
            callback = workoutPlanCb
            workoutPlan = item
        }
    }

}