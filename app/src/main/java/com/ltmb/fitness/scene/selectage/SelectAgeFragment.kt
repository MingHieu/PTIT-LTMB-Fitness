package com.ltmb.fitness.scene.selectage


import android.os.Parcel
import android.os.Parcelable
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectAgeBinding
import com.ltmb.fitness.internal.util.functional.getColorInTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectAgeFragment() : BaseFragment<SelectAgeViewModel, FragmentSelectAgeBinding>(),
    Parcelable {
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

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<SelectAgeFragment> {
        override fun createFromParcel(parcel: Parcel): SelectAgeFragment {
            return SelectAgeFragment(parcel)
        }

        override fun newArray(size: Int): Array<SelectAgeFragment?> {
            return arrayOfNulls(size)
        }
    }

}