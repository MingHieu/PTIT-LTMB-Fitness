package com.ltmb.fitness.scene.getstarted

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentGetStartedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetStartedFragment : BaseFragment<GetStartedViewModel, FragmentGetStartedBinding>() {
    override val layoutId get() = R.layout.fragment_get_started
}