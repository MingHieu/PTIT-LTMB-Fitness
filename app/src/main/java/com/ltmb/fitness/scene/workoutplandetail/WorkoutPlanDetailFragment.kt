package com.ltmb.fitness.scene.workoutplandetail

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutPlanDetailBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.injection.viewmodel.BookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutPlanDetailFragment :
    BaseFragment<WorkoutPlanDetailViewModel, FragmentWorkoutPlanDetailBinding>() {

    override val layoutId get() = R.layout.fragment_workout_plan_detail

    private val args by navArgs<WorkoutPlanDetailFragmentArgs>()

    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun initialize() {
        super.initialize()

        bookmarkViewModel = ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]

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

        viewModel.reloadData.observeNonNull(viewLifecycleOwner) {
            if (it) {
                bookmarkViewModel.getUserBookmarkWorkoutPlanList()
                viewModel.navigate(WorkoutPlanDetailFragmentDirections.toBookmark())
                viewModel.reloadData.value = false
            }
        }
    }
}