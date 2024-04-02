package com.ltmb.fitness.scene.selectheight


import android.os.Parcel
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectHeightBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectHeightFragment() : BaseFragment<SelectHeightViewModel, FragmentSelectHeightBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_height

    constructor(parcel: Parcel) : this()

    override fun initialize() {
        super.initialize()

        val numberPicker = binding.numberPickerAge

        numberPicker.minValue = 100
        numberPicker.maxValue = 200

        numberPicker.value = 170

    }


}