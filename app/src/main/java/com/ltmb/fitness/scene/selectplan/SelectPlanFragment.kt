package com.ltmb.fitness.scene.selectplan


import android.os.Parcel
import androidx.lifecycle.ViewModelProvider
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectPlanBinding
import com.ltmb.fitness.internal.injection.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectPlanFragment() : BaseFragment<SelectPlanViewModel, FragmentSelectPlanBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_plan
    private lateinit var userViewModel: UserViewModel
    private val defaultDayPlan = 1
    override fun initialize() {
        super.initialize()

        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        userViewModel.userModel.dayPlan = defaultDayPlan

        binding.btnSkip.setOnClickListener {
            userViewModel.updateUser()
            viewModel.onClickSkip()
        }

        val numberPicker = binding.numberPickerAge

        numberPicker.minValue = 1
        numberPicker.maxValue = 7

        numberPicker.value = defaultDayPlan

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            userViewModel.userModel.dayPlan = newVal
        }

    }


}