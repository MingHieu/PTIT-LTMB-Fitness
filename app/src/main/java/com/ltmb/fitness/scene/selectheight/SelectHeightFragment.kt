package com.ltmb.fitness.scene.selectheight


import android.os.Parcel
import androidx.lifecycle.ViewModelProvider
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectHeightBinding
import com.ltmb.fitness.internal.injection.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectHeightFragment() : BaseFragment<SelectHeightViewModel, FragmentSelectHeightBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_height
    private lateinit var userViewModel: UserViewModel
    private val defaultHeight = 170
    override fun initialize() {
        super.initialize()

        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        userViewModel.userModel.height = 170

        binding.btnSkip.setOnClickListener {
            userViewModel.updateUser()
            viewModel.onClickSkip()
        }

        val numberPicker = binding.numberPickerAge

        numberPicker.minValue = 50
        numberPicker.maxValue = 220

        numberPicker.value = defaultHeight

        numberPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            userViewModel.userModel.height = newVal
        }

    }


}