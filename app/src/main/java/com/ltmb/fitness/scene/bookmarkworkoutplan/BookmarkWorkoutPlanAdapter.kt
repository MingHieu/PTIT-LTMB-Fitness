package com.ltmb.fitness.scene.bookmarkworkoutplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemBookmarkWorkoutPlanBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.BookmarkWorkoutPlanUiModel

interface BookmarkWorkoutPlanCallback {
    fun onItemClick(id: String)

    fun onItemLongClick(): Boolean

    fun onItemSelectedChanged(id: String, isSelected: Boolean)
}

class BookmarkWorkoutPlanAdapter(private val bookmarkWorkoutPlanCb: BookmarkWorkoutPlanCallback) :
    BaseListAdapter<ItemBookmarkWorkoutPlanBinding, BookmarkWorkoutPlanUiModel>() {

    override val layoutRes: Int get() = R.layout.item_bookmark_workout_plan
    override fun bind(binding: ItemBookmarkWorkoutPlanBinding, item: BookmarkWorkoutPlanUiModel) {
        binding.executeAfter {
            callback = bookmarkWorkoutPlanCb
            bookmarkWorkoutPlan = item
        }
    }

}