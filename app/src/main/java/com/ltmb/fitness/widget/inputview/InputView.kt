package com.ltmb.fitness.widget.inputview

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.ltmb.fitness.R
import com.ltmb.fitness.databinding.ViewInputViewBinding

@BindingAdapter("iv_onClick")
fun setOnClick(input: InputView, cb: () -> Unit) {
    input.onClick = cb
}

@BindingAdapter("iv_onTextChanged")
fun setOnTextChanged(input: InputView, cb: (text: String) -> Unit) {
    input.onTextChanged = cb
}

class InputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), OnClickListener {

    private val binding = ViewInputViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )
    var onClick: (() -> Unit)? = null
    var onTextChanged: ((text: String) -> Unit)? = null

    init {
        readAttributes(attrs)
        bindOnClickListeners()
        setupEditTextTextChangedListener()
    }

    private fun readAttributes(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.InputView,
            0,
            0
        ).apply {
            if (hasValue(R.styleable.InputView_iv_left_icon)) {
                val leftIcon = getResourceId(R.styleable.InputView_iv_left_icon, 0)
                binding.ivLeftIcon.setImageResource(leftIcon)
            } else {
                binding.ivLeftIcon.visibility = GONE
            }

            if (hasValue(R.styleable.InputView_iv_right_icon)) {
                val rightIcon = getResourceId(R.styleable.InputView_iv_right_icon, 0)
                binding.ivRightIcon.setImageResource(rightIcon)
            } else {
                binding.ivRightIcon.visibility = GONE
            }

            if (hasValue(R.styleable.InputView_iv_enabled)) {
                val enabled = getBoolean(R.styleable.InputView_iv_enabled, true)
                with(binding.ivEditText) {
                    isCursorVisible = enabled
                    isFocusable = enabled
                    isClickable = true
                }
            }

            if (hasValue(R.styleable.InputView_android_hint)) {
                binding.ivEditText.hint = getString(R.styleable.InputView_android_hint)
            }

            if (hasValue(R.styleable.InputView_android_inputType)) {
                binding.ivEditText.inputType =
                    getInt(R.styleable.InputView_android_inputType, InputType.TYPE_CLASS_TEXT)
            }
        }
    }

    private fun bindOnClickListeners() {
        for (i in 0..<binding.ivRoot.childCount) {
            binding.ivRoot.getChildAt(i).setOnClickListener(this)
        }
        setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onClick?.invoke()
    }

    private fun setupEditTextTextChangedListener() {
        binding.ivEditText.doOnTextChanged { text, _, _, _ ->
            onTextChanged?.invoke(text.toString())
        }
    }
}