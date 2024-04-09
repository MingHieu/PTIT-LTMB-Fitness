package com.ltmb.fitness.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.ltmb.fitness.BR
import com.ltmb.fitness.R
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.extension.showPopup
import com.ltmb.fitness.navigation.NavigationCommand
import com.ltmb.fitness.scene.main.MainActivity
import java.lang.reflect.ParameterizedType


abstract class BaseFragment<VM : BaseAndroidViewModel, B : ViewDataBinding> : Fragment() {

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
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeNavigation()
        handleBackButton()
        observeLoading()
    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        }
    }

    protected open fun handleNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.ToDirection -> findNavController().navigate(command.directions)

            is NavigationCommand.ToDeepLink -> {
                (activity as? MainActivity)?.navController?.navigate(
                    command.deepLink.toUri(), null, getExtras()
                )
            }

            is NavigationCommand.Popup -> {
                with(command) {
                    context?.showPopup(model, callback)
                }
            }

            is NavigationCommand.Back -> findNavController().navigateUp()
        }
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    private fun handleBackButton() {
        view?.findViewById<ImageView>(R.id.action_back_button)?.setOnClickListener {
            viewModel.navigateBack()
        }
    }

    private val loadingDialog: AlertDialog by lazy {
        AlertDialog.Builder(requireContext(), R.style.TransparentDialog)
            .setView(R.layout.view_loading)
            .setCancelable(false)
            .create()
    }

    private fun observeLoading() {
        viewModel.loading.observeNonNull(viewLifecycleOwner) { isLoading ->
            if (viewModel.useCustomLoading) {
                return@observeNonNull
            }
            if (isLoading) {
                loadingDialog.show()
            } else {
                loadingDialog.hide()
            }
        }
    }
}

