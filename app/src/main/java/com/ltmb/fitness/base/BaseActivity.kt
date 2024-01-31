package com.ltmb.fitness.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ltmb.fitness.BR
import com.ltmb.fitness.internal.util.functional.lazyThreadSafetyNone

abstract class BaseActivity<VM : BaseAndroidViewModel, B : ViewDataBinding> : AppCompatActivity() {

    @get:LayoutRes
    abstract val layoutId: Int

    protected val binding by lazyThreadSafetyNone<B> {
        DataBindingUtil.setContentView(this, layoutId)
    }

    protected abstract val viewModel: VM

    open fun initialize() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        initialize()
    }
}