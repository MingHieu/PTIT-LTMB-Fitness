package com.ltmb.fitness.scene.discover

import android.os.Bundle
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : BaseFragment<DiscoverViewModel, FragmentDiscoverBinding>() {
    override val layoutId get() = R.layout.fragment_discover


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupScreen(getString(R.string.discover_screen_title), false)
    }
}