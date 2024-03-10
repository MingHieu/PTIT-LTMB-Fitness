package com.ltmb.fitness.scene.home

import androidx.navigation.NavOptions
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutId get() = R.layout.fragment_home
    private var isSearchBoxClicked = false

    override fun getNavOptions(): NavOptions {
        if (isSearchBoxClicked) {
            isSearchBoxClicked = false
            return NavOptions.Builder()
                .setEnterAnim(androidx.appcompat.R.anim.abc_fade_in)
                .setPopExitAnim(androidx.appcompat.R.anim.abc_fade_out)
                .build()
        }

        return super.getNavOptions()
    }

    override fun initialize() {
        super.initialize()

        binding.bodyAreaAdapter = BodyAreaAdapter(object : BodyAreaCallback {
            override fun onItemClick() {
                viewModel.onBodyAreaItemClick()
            }
        })

        binding.searchBox.onInputClick = {
            isSearchBoxClicked = true
            viewModel.onSearchBoxClicked()
        }
    }
}