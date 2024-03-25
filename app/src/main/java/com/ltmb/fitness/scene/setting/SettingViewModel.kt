package com.ltmb.fitness.scene.setting

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) : BaseAndroidViewModel(application) {

    fun showConfirmationDialog(context: Context, message: String, onConfirm: () -> Unit) {
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton("Confirm") { dialog, _ ->
                onConfirm.invoke()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    fun goToPersonalInfo() {
        navigate(SettingFragmentDirections.toPersonInfo())
    }

    fun goToWorkoutPreferences() {
        navigate(SettingFragmentDirections.toWorkoutPreferencesFragment())
    }

    fun goToLogout() {
        navigate(SettingFragmentDirections.toLogin())
    }
}