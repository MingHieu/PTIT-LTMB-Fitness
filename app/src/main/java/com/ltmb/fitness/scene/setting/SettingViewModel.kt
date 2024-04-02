package com.ltmb.fitness.scene.setting

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.AuthRepository
import com.ltmb.fitness.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
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

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            navigate(SettingFragmentDirections.toLogin())
        }
    }
}