package com.ltmb.fitness.scene.home

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutId get() = R.layout.fragment_home

    override fun initialize() {
        super.initialize()
        viewModel.isLogin()
    }
}