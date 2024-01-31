package com.ltmb.fitness.scene.setting

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>() {

    override val layoutId get() = R.layout.fragment_setting

}