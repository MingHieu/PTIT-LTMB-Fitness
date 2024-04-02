package com.ltmb.fitness.scene.startapp

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentStartAppBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartAppFragment : BaseFragment<StartAppViewModel, FragmentStartAppBinding>() {

    override val layoutId get() = R.layout.fragment_start_app

    override fun initialize() {
        super.initialize()

        viewModel.authenticate()
    }
}