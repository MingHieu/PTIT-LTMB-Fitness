package com.ltmb.fitness.scene.startapp

import android.app.Application
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
    private val authRepository: AuthRepository
) : BaseAndroidViewModel(application) {

    fun authenticate() {
        viewModelScope.launch {
            delay(1000)
            val currentUser = authRepository.getCurrentUser()
            println("Current user: $currentUser")
            if (currentUser != null) {
                navigate(StartAppFragmentDirections.toHome())
            } else {
                navigate(StartAppFragmentDirections.toAuth())
            }
        }
    }
}
