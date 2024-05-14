package com.ltmb.fitness.widget.inputview

import android.content.Context
import android.graphics.Typeface
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.ltmb.fitness.R
import com.ltmb.fitness.databinding.ViewInputViewBinding
import com.ltmb.fitness.internal.util.functional.setBackgroundRadius

@BindingAdapter("iv_onClick")
fun setOnClick(input: InputView, cb: () -> Unit) {
    input.onInputClick = cb
}

@BindingAdapter("iv_onTextChanged")
fun setOnTextChanged(input: InputView, cb: ((text: String) -> Unit)) {
    input.onTextChanged = cb
}

@BindingAdapter("android:hint")
fun setHint(inputView: InputView, hint: CharSequence) {
    inputView.setHint(hint)
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
    var onInputClick: (() -> Unit)? = null
    var onTextChanged: ((text: String) -> Unit)? = null

    init {
        readAttributes(attrs)
        bindOnClickListeners()
        listenEditTextTextChanged()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputView)
        val textSizePx =
            typedArray.getDimensionPixelSize(R.styleable.InputView_android_textSize, -1)
        if (textSizePx != -1) {
            binding.ivEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx.toFloat())
        }
        typedArray.recycle()


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

            if (hasValue(R.styleable.InputView_iv_left_icon_tint)) {
                val leftIconTint = getResourceId(R.styleable.InputView_iv_left_icon_tint, 0)
                binding.ivLeftIcon.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        leftIconTint
                    )
                )
            }

            if (hasValue(R.styleable.InputView_iv_right_icon)) {
                val rightIcon = getResourceId(R.styleable.InputView_iv_right_icon, 0)
                binding.ivRightIcon.setImageResource(rightIcon)
            } else {
                binding.ivRightIcon.visibility = GONE
            }

            if (hasValue(R.styleable.InputView_iv_right_icon_tint)) {
                val rightIconTint = getResourceId(R.styleable.InputView_iv_right_icon_tint, 0)
                binding.ivRightIcon.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        rightIconTint
                    )
                )
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

            if (hasValue(R.styleable.InputView_iv_background)) {
                val backgroundColor = getResourceId(R.styleable.InputView_iv_background, 0)
                setBackgroundRadius(
                    binding.ivRoot,
                    24f,
                    ContextCompat.getColor(
                        context,
                        backgroundColor
                    )
                )
            }

            if (hasValue(R.styleable.InputView_android_textColor)) {
                val textColor = getResourceId(
                    R.styleable.InputView_android_textColor,
                    0
                )
                binding.ivEditText.setTextColor(
                    ContextCompat.getColor(
                        context,
                        textColor
                    )
                )
            }

            if (hasValue(R.styleable.InputView_android_textColorHint)) {
                val hintTextColor = getResourceId(
                    R.styleable.InputView_android_textColorHint,
                    0
                )
                binding.ivEditText.setHintTextColor(
                    ContextCompat.getColor(
                        context,
                        hintTextColor
                    )
                )
            }

            if (hasValue(R.styleable.InputView_android_minLines)) {
                val lines = getInt(R.styleable.InputView_android_minLines, 1)
                binding.ivEditText.minLines = lines
                binding.ivEditText.maxLines = 5
            }

            if (hasValue(R.styleable.InputView_android_textSize)) {
                val textSize = getResourceId(
                    R.styleable.InputView_android_textSize,
                    0
                )
                // Kiểm tra nếu textSize là một dimen resource ID
                if (textSize != 0 && resources.getResourceTypeName(textSize) == "dimen") {
                    val textSizePx = resources.getDimensionPixelSize(textSize)
                    binding.ivEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx.toFloat())
                }
            }

            if (hasValue(R.styleable.InputView_android_textStyle)) {
                val textStyle = getInt(
                    R.styleable.InputView_android_textStyle,
                    Typeface.NORMAL
                )
                binding.ivEditText.setTypeface(null, textStyle)
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
        onInputClick?.invoke() ?: focus()
    }

    private fun listenEditTextTextChanged() {
        binding.ivEditText.doOnTextChanged { text, _, _, _ ->
            onTextChanged?.invoke(text.toString())
        }
    }

    fun focus() {
        binding.ivEditText.postDelayed({
            binding.ivEditText.requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.ivEditText, InputMethodManager.SHOW_IMPLICIT)
        }, 100)
    }

    fun setValue(value: String) {
        binding.ivEditText.setText(value)
    }

    fun setHint(hint: CharSequence) {
        binding.ivEditText.setHint(hint)
    }
}
