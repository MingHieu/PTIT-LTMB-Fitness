package com.ltmb.fitness.scene.createworkoutplan

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentCreateWorkoutPlanBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.injection.viewmodel.BookmarkViewModel
import com.ltmb.fitness.widget.workoutselection.WorkoutSelectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateWorkoutPlanFragment :
    BaseFragment<CreateWorkoutPlanViewModel, FragmentCreateWorkoutPlanBinding>() {

    override val layoutId get() = R.layout.fragment_create_workout_plan


    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun initialize() {
        super.initialize()

        bookmarkViewModel = ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]

        binding.nameInput.setValue(viewModel.model.name)

        binding.descriptionInput.setValue(viewModel.model.description)

        binding.nameInput.onTextChanged = { text -> viewModel.model.name = text }

        binding.descriptionInput.onTextChanged = { text -> viewModel.model.description = text }

        binding.addWorkoutButton.setOnClickListener {
            val workoutSelectionFragment = WorkoutSelectionFragment()
            workoutSelectionFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.TransparentDialog
            )
            workoutSelectionFragment.show(parentFragmentManager, workoutSelectionFragment.tag)
        }

        viewModel.reloadData.observeNonNull(viewLifecycleOwner) {
            if (it) {
                bookmarkViewModel.getUserBookmarkWorkoutPlanList()
                viewModel.navigateBack()
            }
        }
    }
}