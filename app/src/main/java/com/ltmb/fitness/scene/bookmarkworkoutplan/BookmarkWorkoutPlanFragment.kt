package com.ltmb.fitness.scene.bookmarkworkoutplan

import androidx.lifecycle.ViewModelProvider
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentBookmarkWorkoutPlanBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.injection.viewmodel.BookmarkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkWorkoutPlanFragment :
    BaseFragment<BookmarkWorkoutPlanViewModel, FragmentBookmarkWorkoutPlanBinding>() {

    override val layoutId get() = R.layout.fragment_bookmark_workout_plan

    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun initialize() {
        super.initialize()

        bookmarkViewModel = ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]

        binding.bookmarkViewModel = bookmarkViewModel

        binding.adapter = BookmarkWorkoutPlanAdapter(object : BookmarkWorkoutPlanCallback {
            override fun onItemClick(id: String) {
                viewModel.onItemClick(id)
            }

            override fun onItemLongClick(): Boolean {
                bookmarkViewModel.setSelectingValue(true)
                return true
            }

            override fun onItemSelectedChanged(id: String, isSelected: Boolean) {
                bookmarkViewModel.changeItemSelected(id, isSelected)
            }
        })

        binding.workoutPlanRecyclerView.itemAnimator = null

        bookmarkViewModel.selecting.observeNonNull(viewLifecycleOwner) {
            bookmarkViewModel.changeItemSelecting(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bookmarkViewModel.selecting.value = false
    }
}