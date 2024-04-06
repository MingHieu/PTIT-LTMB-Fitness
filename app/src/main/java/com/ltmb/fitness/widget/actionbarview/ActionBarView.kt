package com.ltmb.fitness.widget.actionbarview

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.databinding.ViewActionBarViewBinding

@BindingAdapter("abv_onRightIconClick")
fun setOnRightIconClick(actionBarView: ActionBarView, cb: () -> Unit) {
    actionBarView.onRightIconClick = cb
}

@BindingAdapter("abv_viewModel")
fun setViewModel(actionBarView: ActionBarView, vm: BaseAndroidViewModel) {
    actionBarView.viewModel = vm
}

@BindingAdapter("abv_title")
fun setAbvTitle(actionBarView: ActionBarView, title: String?) {
    actionBarView.setTitle(title.orEmpty())
}

class ActionBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding = ViewActionBarViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var viewModel: BaseAndroidViewModel? = null
    var onRightIconClick: (() -> Unit)? = null

    init {
        readAttributes(attrs)
    }

    private fun readAttributes(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ActionBarView,
            0,
            0
        ).apply {
            if (hasValue(R.styleable.ActionBarView_abv_left_icon)) {
                val resId = getResourceId(R.styleable.ActionBarView_abv_left_icon, 0)
                binding.abvLeftIcon.setImageResource(resId)
            }

            val canGoBack = getBoolean(R.styleable.ActionBarView_abv_can_go_back, true)
            if (canGoBack) {
                binding.abvLeftIcon.setOnClickListener {
                    viewModel?.navigateBack()
                }
            } else {
                binding.abvLeftIcon.visibility = View.GONE
            }

            if (hasValue(R.styleable.ActionBarView_abv_title)) {
                val text = getString(R.styleable.ActionBarView_abv_title)
                binding.abvTitle.text = text
            }

            if (hasValue(R.styleable.ActionBarView_abv_right_icon)) {
                val resId = getResourceId(R.styleable.ActionBarView_abv_right_icon, 0)
                binding.abvRightIcon.setImageResource(resId)
                binding.abvRightIcon.setOnClickListener {
                    onRightIconClick?.invoke()
                }
            }

            if (hasValue(R.styleable.ActionBarView_abv_color)) {
                val resId = getResourceId(R.styleable.ActionBarView_abv_color, 0)
                binding.abvLeftIcon.setColorFilter(
                    ContextCompat.getColor(context, resId),
                    PorterDuff.Mode.SRC_IN
                )
                binding.abvTitle.setTextColor(ContextCompat.getColor(context, resId))
                binding.abvRightIcon.setColorFilter(
                    ContextCompat.getColor(context, resId),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    fun setTitle(text: String) {
        binding.abvTitle.text = text
    }
}