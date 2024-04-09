package com.ltmb.fitness.scene.selectgender

import android.graphics.PorterDuff
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectGenderBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.injection.viewmodel.BookmarkViewModel
import com.ltmb.fitness.internal.injection.viewmodel.UserViewModel
import com.ltmb.fitness.internal.util.functional.getColorInTheme
import com.ltmb.fitness.uimodel.GenderUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectGenderFragment : BaseFragment<SelectGenderViewModel, FragmentSelectGenderBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_gender

    private lateinit var userViewModel: UserViewModel


    override fun initialize() {
        super.initialize()

        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        userViewModel.userModel.gender = viewModel.genderSelection.value!!

        binding.btnSkip.setOnClickListener {
            userViewModel.updateUser()
            viewModel.onClickSkip()
        }

        binding.maleSelection.setOnClickListener {
            viewModel.genderSelection.value = GenderUiModel.MALE
            userViewModel.userModel.gender = GenderUiModel.MALE
        }

        binding.femaleSelection.setOnClickListener {
            viewModel.genderSelection.value = GenderUiModel.FEMALE
            userViewModel.userModel.gender = GenderUiModel.FEMALE
        }

        viewModel.genderSelection.observeNonNull(viewLifecycleOwner) {
            val primaryColor =
                getColorInTheme(requireContext(), androidx.appcompat.R.attr.colorPrimary)
            val secondaryColor = ContextCompat.getColor(requireContext(), R.color.bombay)

            when (it) {
                GenderUiModel.MALE -> {
                    binding.maleSelectionShadow.setColorFilter(primaryColor, PorterDuff.Mode.SRC_IN)
                    binding.femaleSelectionShadow.setColorFilter(
                        secondaryColor,
                        PorterDuff.Mode.SRC_IN
                    )
                }

                GenderUiModel.FEMALE -> {
                    binding.femaleSelectionShadow.setColorFilter(
                        primaryColor,
                        PorterDuff.Mode.SRC_IN
                    )
                    binding.maleSelectionShadow.setColorFilter(
                        secondaryColor,
                        PorterDuff.Mode.SRC_IN
                    )
                }
            }
        }


    }
}