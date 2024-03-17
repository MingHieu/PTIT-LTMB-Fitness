package com.ltmb.fitness.base

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ltmb.fitness.BR
import java.lang.reflect.ParameterizedType


abstract class BaseBottomSheetDialogFragment<VM : BaseAndroidViewModel, B : ViewDataBinding> :
    BottomSheetDialogFragment() {

    @get:LayoutRes
    abstract val layoutId: Int

    protected lateinit var binding: B

    open fun initialize() {}

    @Suppress("UNCHECKED_CAST")
    protected open val viewModel: VM
        get() {
            val persistentViewModelClass =
                (javaClass.genericSuperclass as ParameterizedType)
                    .actualTypeArguments[0] as Class<VM>
            val lazyVM = createViewModelLazy(
                viewModelClass = persistentViewModelClass.kotlin,
                storeProducer = { viewModelStore },
                factoryProducer = {
                    (this as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory
                        ?: defaultViewModelProviderFactory
                }
            )
            return lazyVM.value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        initialize()

        binding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val v = binding.root.findFocus()
                if (v is EditText) {
                    val outRect = Rect()
                    v.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        v.clearFocus()
                        val imm =
                            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.windowToken, 0)
                    }
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                // Handle click event
                view?.performClick()
            }
            false
        }

        return binding.root
    }
}

