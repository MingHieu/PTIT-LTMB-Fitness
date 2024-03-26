package com.ltmb.fitness.scene.selectage


import android.os.Parcel
import android.os.Parcelable
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectAgeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectAgeFragment() : BaseFragment<SelectAgeViewModel, FragmentSelectAgeBinding>() {
    override val layoutId: Int get() = R.layout.fragment_select_age

    constructor(parcel: Parcel) : this() {
    }

    override fun initialize() {
        super.initialize()

        val numberPicker = binding.numberPickerAge

        numberPicker.minValue = 10
        numberPicker.maxValue = 100

        numberPicker.value = 18

    }


}