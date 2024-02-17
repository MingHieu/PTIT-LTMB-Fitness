package com.ltmb.fitness.scene.search

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemSearchBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.SearchUiModel
import com.squareup.picasso.Picasso

interface SearchCallback {
    fun onSearchItemClick()
}

class SearchAdapter(private val searchCb: SearchCallback) :
    BaseListAdapter<ItemSearchBinding, SearchUiModel>() {

    override val layoutRes: Int get() = R.layout.item_search
    override fun bind(binding: ItemSearchBinding, item: SearchUiModel) {
        binding.executeAfter {
            Picasso.get()
                .load(item.getImagePath())
                .placeholder(R.drawable.animation_skeleton)
                .error(R.drawable.img_exercise_sample)
                .into(thumbnail)
            callback = searchCb
            search = item
        }
    }

}