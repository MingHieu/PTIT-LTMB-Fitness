package com.ltmb.fitness.scene.selectgender

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSelectGenderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectGenderFragment : BaseFragment<SelectGenderViewModel, FragmentSelectGenderBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_select_gender
}