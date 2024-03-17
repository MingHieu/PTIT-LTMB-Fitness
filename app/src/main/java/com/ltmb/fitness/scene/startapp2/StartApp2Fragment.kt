package com.ltmb.fitness.scene.startapp2

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentStartApp2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartApp2Fragment : BaseFragment<StartApp2ViewModel, FragmentStartApp2Binding>() {

    override val layoutId get() = R.layout.fragment_start_app_2

}