package com.ltmb.fitness.scene.signup

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<SignupViewModel, FragmentSignupBinding>() {
    override val layoutId get() = R.layout.fragment_signup

    override fun initialize() {
        super.initialize()

        binding.inputEmail.onTextChanged = { text ->
            viewModel.setEmail(text)
        }

        binding.inputPassword.onTextChanged = { text ->
            viewModel.setPassword(text)
        }

        binding.inputConfirmPassword.onTextChanged = { text ->
            viewModel.setConfirmPassword(text)
        }
    }

}