package com.ltmb.fitness.scene.signup

import android.widget.Toast
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSignupBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<SignupViewModel, FragmentSignupBinding>() {
    override val layoutId get() = R.layout.fragment_signup

    override fun initialize() {
        super.initialize()

        binding.inputEmail.setValue(viewModel.getEmail())
        binding.inputPassword.setValue(viewModel.getPassword())
        binding.inputConfirmPassword.setValue(viewModel.getConfirmPassword())

        binding.inputEmail.onTextChanged = { text ->
            viewModel.setEmail(text)
        }

        binding.inputPassword.onTextChanged = { text ->
            viewModel.setPassword(text)
        }

        binding.inputConfirmPassword.onTextChanged = { text ->
            viewModel.setConfirmPassword(text)
        }

        viewModel.isShowToast.observeNonNull(viewLifecycleOwner) { isHandled ->
            if (isHandled) {
                Toast.makeText(requireContext(), viewModel.messageError, Toast.LENGTH_LONG).show()
                viewModel.isShowToast.value = false
            }
        }
    }

}