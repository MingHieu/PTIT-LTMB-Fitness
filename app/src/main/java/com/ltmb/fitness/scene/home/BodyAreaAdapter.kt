package com.ltmb.fitness.scene.home

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemBodyAreaBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.BodyAreaUiModel

interface BodyAreaCallback {
    fun onItemClick(bodyArea: BodyAreaUiModel)
}

class BodyAreaAdapter(private val bodyAreaCb: BodyAreaCallback) :
    BaseListAdapter<ItemBodyAreaBinding, BodyAreaUiModel>() {

    override val layoutRes: Int get() = R.layout.item_body_area

    override fun bind(binding: ItemBodyAreaBinding, item: BodyAreaUiModel) {
        binding.executeAfter {
            callback = bodyAreaCb
            bodyArea = item
        }
    }

}