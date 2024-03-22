package com.ltmb.fitness.scene.search

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemKeySearchHistoryBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.KeySearchUiModel

interface KeySearchCallback {
    fun onItemClick(keySearch: String)

    fun onDeleteItem(id: String)
}

class KeySearchHistoryAdapter(private val keySearchCb: KeySearchCallback) :
    BaseListAdapter<ItemKeySearchHistoryBinding, KeySearchUiModel>() {

    override val layoutRes: Int get() = R.layout.item_key_search_history
    override fun bind(binding: ItemKeySearchHistoryBinding, item: KeySearchUiModel) {
        binding.executeAfter {
            callback = keySearchCb
            keySearch = item
        }
    }

}