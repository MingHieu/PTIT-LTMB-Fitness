package com.ltmb.fitness.scene.workoutplandetail

import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutPlanDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutPlanDetailFragment :
    BaseFragment<WorkoutPlanDetailViewModel, FragmentWorkoutPlanDetailBinding>() {

    override val layoutId get() = R.layout.fragment_workout_plan_detail
    private val args by navArgs<WorkoutPlanDetailFragmentArgs>()

    override fun initialize() {
        super.initialize()

        viewModel.getWorkoutPlanDetail(args.workoutPlanId)

        binding.workoutAdapter = WorkoutAdapter()

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