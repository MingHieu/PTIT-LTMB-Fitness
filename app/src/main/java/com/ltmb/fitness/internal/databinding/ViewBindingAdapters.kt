package com.ltmb.fitness.internal.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.base.ListAdapterItem

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun <T : ListAdapterItem> submitList(view: RecyclerView, list: List<T>?) {
    val adapter = view.adapter as BaseListAdapter<ViewDataBinding, T>?
    adapter?.submitList(list)
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: BaseListAdapter<ViewDataBinding, ListAdapterItem>?) {
    adapter?.let {
        view.adapter = it
    }
}