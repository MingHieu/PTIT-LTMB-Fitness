package com.ltmb.fitness.scene.startapp

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartAppViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository,
    private val sharedPreferences: SharedPreferences
) : BaseAndroidViewModel(application) {

    fun authenticate() {
        viewModelScope.launch {
            delay(1000)
            val currentUser = authRepository.getCurrentUser()
            if (currentUser != null) {
                println("Current user: ${currentUser.uid}")
            }
            if (currentUser != null) {
                navigate(StartAppFragmentDirections.toHome())
            } else {
                val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)
                if (isFirstRun) {
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("isFirstRun", false)
                    editor.apply()
                    navigate(StartAppFragmentDirections.toStartApp1())
                } else {
                    navigate(StartAppFragmentDirections.toAuth())
                }
            }
        }
    }
}
