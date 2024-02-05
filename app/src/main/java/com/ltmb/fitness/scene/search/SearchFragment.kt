package com.ltmb.fitness.scene.search

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(), SearchCallback {

    override val layoutId get() = R.layout.fragment_search

    override fun initialize() {
        super.initialize()

        binding.searchAdapter = SearchAdapter(this)
        binding.searchBox.focus()
    }

    override fun onSearchItemClick() {
        TODO("Not yet implemented")
    }
}