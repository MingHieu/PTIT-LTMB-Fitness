package com.ltmb.fitness.scene.home

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemWorkoutPlanHomeBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.BookmarkWorkoutPlanUiModel

interface BookmarkWorkoutPlanCallback {
    fun onItemClick(id: String)
}

class BookmarkWorkoutPlanAdapter(private val bookmarkWorkoutPlanCb: BookmarkWorkoutPlanCallback) :
    BaseListAdapter<ItemWorkoutPlanHomeBinding, BookmarkWorkoutPlanUiModel>() {

    override val layoutRes: Int get() = R.layout.item_workout_plan_home

    override fun bind(binding: ItemWorkoutPlanHomeBinding, item: BookmarkWorkoutPlanUiModel) {
        binding.executeAfter {
            callback = bookmarkWorkoutPlanCb
            workoutPlan = item
        }
    }
}