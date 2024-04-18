package com.ltmb.fitness.scene.setting

import android.app.AlertDialog
import android.content.Context
import androidx.core.content.ContextCompat
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>() {

    override val layoutId get() = R.layout.fragment_setting

    override fun initialize() {
        super.initialize()

        binding.btnLogout.setOnClickListener {
            confirmLogoutDialog(requireContext(), "Are you sure you want to sign out?") {
                viewModel.logout()
            }
        }

    }

    private fun confirmLogoutDialog(context: Context, message: String, onConfirm: () -> Unit) {

        val dialog = AlertDialog.Builder(context)
            .setMessage(message)
            .setNegativeButton("Confirm") { dialog, _ ->
                onConfirm.invoke()
                dialog.dismiss()
            }
            .setPositiveButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.red))
        }

        dialog.show()
    }

}