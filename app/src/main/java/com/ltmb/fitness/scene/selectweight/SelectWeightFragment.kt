package com.ltmb.fitness.scene.selectweight


import android.os.Parcel
import android.os.Parcelable
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectWeightBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectWeightFragment() : BaseFragment<SelectWeightViewModel, FragmentSelectWeightBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_weight

    constructor(parcel: Parcel) : this() {
    }

    override fun initialize() {
        super.initialize()

        val numberPicker = binding.numberPickerAge

        numberPicker.minValue = 30
        numberPicker.maxValue = 100

        numberPicker.value = 60

    }


}