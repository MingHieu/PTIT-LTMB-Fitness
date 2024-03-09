package com.ltmb.fitness.internal.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.base.ListAdapterItem
import com.squareup.picasso.Picasso

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

@BindingAdapter("itemTouchHelper")
fun setItemTouchHelper(view: RecyclerView, itemTouchHelper: ItemTouchHelper) {
    itemTouchHelper.attachToRecyclerView(view)
}

@BindingAdapter("imageResource")
fun setImageResource(view: ImageView, res: Int) {
    view.setImageResource(res)
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.animation_skeleton)
        .error(R.drawable.animation_skeleton)
        .into(view)
}