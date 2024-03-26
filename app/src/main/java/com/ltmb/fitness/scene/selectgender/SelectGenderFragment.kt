package com.ltmb.fitness.scene.selectgender

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectGenderBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.util.functional.getColorInTheme
import com.ltmb.fitness.uimodel.GenderUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectGenderFragment : BaseFragment<SelectGenderViewModel, FragmentSelectGenderBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_gender

    override fun initialize() {
        super.initialize()

        binding.maleSelection.setOnClickListener {
            viewModel.genderSelection.value = GenderUiModel.MALE
        }

        binding.femaleSelection.setOnClickListener {
            viewModel.genderSelection.value = GenderUiModel.FEMALE
        }

        viewModel.genderSelection.observeNonNull(viewLifecycleOwner) {
            val primaryColor = getColorInTheme(requireContext(), androidx.appcompat.R.attr.colorPrimary)
            val secondaryColor = ContextCompat.getColor(requireContext(), R.color.bombay)

            when (it) {
                GenderUiModel.MALE -> {
                    binding.maleSelectionShadow.setColorFilter(primaryColor, PorterDuff.Mode.SRC_IN)
                    binding.femaleSelectionShadow.setColorFilter(secondaryColor, PorterDuff.Mode.SRC_IN)
                }
                GenderUiModel.FEMALE -> {
                    binding.femaleSelectionShadow.setColorFilter(primaryColor, PorterDuff.Mode.SRC_IN)
                    binding.maleSelectionShadow.setColorFilter(secondaryColor, PorterDuff.Mode.SRC_IN)
                }
            }
        }
    }
}