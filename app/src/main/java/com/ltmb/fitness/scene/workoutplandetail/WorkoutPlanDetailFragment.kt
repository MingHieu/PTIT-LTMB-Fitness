package com.ltmb.fitness.scene.workoutplandetail

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutPlanDetailBinding

class WorkoutPlanDetailFragment :
    BaseFragment<WorkoutPlanDetailViewModel, FragmentWorkoutPlanDetailBinding>() {

    override val layoutId get() = R.layout.fragment_workout_plan_detail

    override fun initialize() {
        super.initialize()

        binding.workoutAdapter =
            WorkoutAdapter(object : WorkoutCallback {
                override fun onItemClick() {
                    TODO("Not yet implemented")
                }
            })

        binding.itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP, 0) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val initial = viewHolder.adapterPosition
                val final = target.adapterPosition
                viewModel.swapItemInWorkoutList(initial, final)

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }
        })
    }
}