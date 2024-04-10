package com.ltmb.fitness.scene.selectweight


import android.os.Parcel
import androidx.lifecycle.ViewModelProvider
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectWeightBinding
import com.ltmb.fitness.internal.injection.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectWeightFragment() : BaseFragment<SelectWeightViewModel, FragmentSelectWeightBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_weight
    private lateinit var userViewModel: UserViewModel
    private val defaultWeight= 50
    override fun initialize() {
        super.initialize()

        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        userViewModel.userModel.weight = defaultWeight

        binding.btnSkip.setOnClickListener {
            userViewModel.updateUser()
            viewModel.onClickSkip()
        }

        val numberPicker = binding.numberPickerAge

        numberPicker.minValue = 10
        numberPicker.maxValue = 250

        numberPicker.value = defaultWeight

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            userViewModel.userModel.weight = newVal
        }

    }


}