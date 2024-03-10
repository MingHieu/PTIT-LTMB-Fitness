package com.ltmb.fitness.scene.search

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemSearchBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.SearchUiModel

interface SearchCallback {
    fun onSearchItemClick()
}

class SearchAdapter(private val searchCb: SearchCallback) :
    BaseListAdapter<ItemSearchBinding, SearchUiModel>() {

    override val layoutRes: Int get() = R.layout.item_search
    override fun bind(binding: ItemSearchBinding, item: SearchUiModel) {
        binding.executeAfter {
            callback = searchCb
            search = item
        }
    }

}