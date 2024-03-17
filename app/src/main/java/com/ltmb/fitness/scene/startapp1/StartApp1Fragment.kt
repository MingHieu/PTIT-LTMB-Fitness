package com.ltmb.fitness.scene.startapp1

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentStartApp1Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartApp1Fragment : BaseFragment<StartApp1ViewModel, FragmentStartApp1Binding>() {

    override val layoutId get() = R.layout.fragment_start_app_1

}