package com.ltmb.fitness.scene.home

import androidx.navigation.NavOptions
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutId get() = R.layout.fragment_home

    override fun getNavOptions(): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(androidx.appcompat.R.anim.abc_fade_in)
            .setPopExitAnim(androidx.appcompat.R.anim.abc_fade_out)
            .build()
    }

    override fun initialize() {
        super.initialize()

        binding.bodyAreaAdapter = BodyAreaAdapter(object : BodyAreaCallback {
            override fun onItemClick() {
                TODO("Not yet implemented")
            }
        })
    }
}