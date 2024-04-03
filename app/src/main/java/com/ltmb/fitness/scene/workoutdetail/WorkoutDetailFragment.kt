package com.ltmb.fitness.scene.workoutdetail

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentWorkoutDetailBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.util.functional.getColorInTheme
import com.ltmb.fitness.uimodel.TutorialType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutDetailFragment : BaseFragment<WorkoutDetailViewModel, FragmentWorkoutDetailBinding>() {

    override val layoutId get() = R.layout.fragment_workout_detail
    private val args by navArgs<WorkoutDetailFragmentArgs>()

    override fun initialize() {
        super.initialize()

        viewModel.workoutPlanDetail = args.workoutPlanDetail

        viewModel.setWorkoutList(args.workoutList.toList())

        viewModel.tutorialType.observeNonNull(viewLifecycleOwner) {
            val textColor = getColorInTheme(requireContext(), R.attr.colorText)
            val primaryColor =
                getColorInTheme(requireContext(), androidx.appcompat.R.attr.colorPrimary)
            val onPrimaryColor =
                getColorInTheme(requireContext(), com.google.android.material.R.attr.colorOnPrimary)
            val transparentColor = ContextCompat.getColor(requireContext(), R.color.transparent)

            when (it) {
                TutorialType.TEXT -> {
                    binding.textSelection.backgroundTintList = ColorStateList.valueOf(primaryColor)
                    binding.textSelectionTitle.setTextColor(onPrimaryColor)
                    binding.videoSelection.backgroundTintList =
                        ColorStateList.valueOf(transparentColor)
                    binding.videoSelectionTitle.setTextColor(textColor)
                }

                TutorialType.VIDEO -> {
                    binding.videoSelection.backgroundTintList = ColorStateList.valueOf(primaryColor)
                    binding.videoSelectionTitle.setTextColor(onPrimaryColor)
                    binding.textSelection.backgroundTintList =
                        ColorStateList.valueOf(transparentColor)
                    binding.textSelectionTitle.setTextColor(textColor)
                }
            }
        }

        binding.pauseButton.setOnClickListener {
            viewModel.paused.value = !viewModel.paused.value!!
        }

        viewModel.paused.observeNonNull(viewLifecycleOwner) { isPaused ->
            if (isPaused) {
                viewModel.stopCountDown()
                binding.pauseButton.text = getString(R.string.common_resume)
                binding.pauseButton.icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_play)
            } else {
                viewModel.runCountDown()
                binding.pauseButton.text = getString(R.string.common_pause)
                binding.pauseButton.icon =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_pause)
            }
        }
    }
}