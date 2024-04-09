package com.ltmb.fitness.scene.login

import android.widget.Toast
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentLoginBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override val layoutId get() = R.layout.fragment_login

    override fun initialize() {
        super.initialize()

        binding.inputEmail.setValue(viewModel.getEmail())
        binding.inputPassword.setValue(viewModel.getPassword())

        binding.inputEmail.onTextChanged = { text ->
            viewModel.setEmail(text)
        }

        binding.inputPassword.onTextChanged = { text ->
            viewModel.setPassword(text)
        }

        viewModel.isShowToast.observeNonNull(viewLifecycleOwner) { isHandled ->
            if (isHandled) {
                Toast.makeText(requireContext(), viewModel.messageError, Toast.LENGTH_LONG).show()
                viewModel.isShowToast.value = false
            }
        }
    }
}