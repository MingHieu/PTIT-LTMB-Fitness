package com.ltmb.fitness.scene.selectplan


import android.os.Parcel
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectPlanBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectPlanFragment() : BaseFragment<SelectPlanViewModel, FragmentSelectPlanBinding>(){
    override val layoutId: Int get() = R.layout.fragment_select_plan

    constructor(parcel: Parcel) : this() {
    }

    override fun initialize() {
        super.initialize()

        val numberPicker = binding.numberPickerAge

        numberPicker.minValue = 1
        numberPicker.maxValue = 7

        numberPicker.value = 4

    }




}