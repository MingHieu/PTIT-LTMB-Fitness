package com.ltmb.fitness.scene.bookmarkworkoutplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentBookmarkWorkoutPlanBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkWorkoutPlanFragment :
    BaseFragment<BookmarkWorkoutPlanViewModel, FragmentBookmarkWorkoutPlanBinding>() {
    override val layoutId get() = R.layout.fragment_bookmark_workout_plan

    override fun initialize() {
        super.initialize()

        binding.adapter = BookmarkWorkoutPlanAdapter(object : BookmarkWorkoutPlanCallback {
            override fun onItemClick() {

            }

            override fun onItemLongClick(): Boolean {
                viewModel.setSelectingValue(true)
                return true
            }

            override fun onItemSelectedChanged(id: Long, isSelected: Boolean) {
                viewModel.changeItemSelected(id, isSelected)
            }
        })

        binding.workoutPlanRecyclerView.itemAnimator = null

        viewModel.selecting.observeNonNull(viewLifecycleOwner) {
            viewModel.changeItemSelecting(it)
        }
    }
}