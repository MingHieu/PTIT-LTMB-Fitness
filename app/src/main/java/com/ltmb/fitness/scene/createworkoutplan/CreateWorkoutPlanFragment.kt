package com.ltmb.fitness.scene.createworkoutplan

import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.data.remote.model.workoutplan.BookmarkWorkoutPlanModel
import com.ltmb.fitness.databinding.FragmentCreateWorkoutPlanBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.injection.viewmodel.BookmarkViewModel
import com.ltmb.fitness.widget.workoutselection.ItemSelectedChangeListener
import com.ltmb.fitness.widget.workoutselection.WorkoutCallback
import com.ltmb.fitness.widget.workoutselection.WorkoutSelectionAdapter
import com.ltmb.fitness.widget.workoutselection.WorkoutSelectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateWorkoutPlanFragment :
    BaseFragment<CreateWorkoutPlanViewModel, FragmentCreateWorkoutPlanBinding>() {

    override val layoutId get() = R.layout.fragment_create_workout_plan

    private lateinit var bookmarkViewModel: BookmarkViewModel

    private val args by navArgs<CreateWorkoutPlanFragmentArgs>()

    override fun initialize() {
        super.initialize()

        bookmarkViewModel = ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]

        val workoutPlan = args.workoutPlan
        if (workoutPlan != null) {
            binding.actionBar.setTitle(getString(R.string.update_workout_plan_screen_title))
            viewModel.model = BookmarkWorkoutPlanModel(
                id = workoutPlan.id,
                name = workoutPlan.name,
                description = workoutPlan.description,
                thumbnail = workoutPlan.thumbnail,
                level = workoutPlan.level,
                duration = workoutPlan.duration,
                kcal = workoutPlan.kcal,
                workoutIds = workoutPlan.workouts.map { it.id }
            )
            viewModel.updateWorkoutListSelected()
        }

        binding.nameInput.setValue(viewModel.model.name)

        binding.descriptionInput.setValue(viewModel.model.description)

        binding.nameInput.onTextChanged = { text -> viewModel.model.name = text }

        binding.descriptionInput.onTextChanged = { text -> viewModel.model.description = text }

        binding.addWorkoutButton.setOnClickListener {
            val workoutSelectionFragment =
                WorkoutSelectionFragment(viewModel.workouts.value.orEmpty())
            workoutSelectionFragment.setItemSelectedChangedListener(object :
                ItemSelectedChangeListener {
                override fun onChanged(id: String, isSelected: Boolean) {
                    viewModel.onItemSelectedChanged(id, isSelected)
                }
            })
            workoutSelectionFragment.setStyle(
                DialogFragment.STYLE_NORMAL,
                R.style.TransparentDialog
            )
            workoutSelectionFragment.show(parentFragmentManager, workoutSelectionFragment.tag)
        }

        binding.adapter = WorkoutSelectionAdapter(object : WorkoutCallback {
            override fun onItemClick() {

            }

            override fun onItemSelectedChanged(id: String, isSelected: Boolean) {
                viewModel.onItemSelectedChanged(id, isSelected)
            }

        })

        viewModel.workouts.observeNonNull(viewLifecycleOwner) {
            viewModel.workoutsSelected.value = it.filter { item -> item.selected }
        }

        viewModel.toastText.observeNonNull(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.toastText.value = ""
            }
        }

        viewModel.reloadData.observeNonNull(viewLifecycleOwner) {
            if (it) {
                bookmarkViewModel.getUserBookmarkWorkoutPlanList()
                viewModel.navigateBack()
            }
        }
    }
}