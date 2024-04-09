package com.ltmb.fitness.scene.search

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemSearchBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.ExerciseSearchUiModel

interface SearchCallback {
    fun onSearchItemClick(id: String)
}

class SearchAdapter(private val searchCb: SearchCallback) :
    BaseListAdapter<ItemSearchBinding, ExerciseSearchUiModel>() {

    override val layoutRes: Int get() = R.layout.item_search
    override fun bind(binding: ItemSearchBinding, item: ExerciseSearchUiModel) {
        binding.executeAfter {
            callback = searchCb
            search = item
        }
    }

}