package com.ltmb.fitness.scene.selectage


import android.os.Parcel
import androidx.lifecycle.ViewModelProvider
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectAgeBinding
import com.ltmb.fitness.internal.injection.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectAgeFragment() : BaseFragment<SelectAgeViewModel, FragmentSelectAgeBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_age
    private lateinit var userViewModel: UserViewModel

    private val defaultAge = 18

    override fun initialize() {
        super.initialize()

        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        userViewModel.userModel.age = defaultAge

        binding.btnSkip.setOnClickListener {
            userViewModel.updateUser()
            viewModel.onClickSkip()
        }

        val numberPicker = binding.numberPickerAge

        numberPicker.minValue = 10
        numberPicker.maxValue = 100

        numberPicker.value = defaultAge

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            userViewModel.userModel.age = newVal
        }

    }


}