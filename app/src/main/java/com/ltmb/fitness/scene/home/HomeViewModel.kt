package com.ltmb.fitness.scene.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val authRepository: AuthRepository,
) : BaseAndroidViewModel(application) {

    val num = MutableLiveData<Number>(0)

    fun onIncreaseClick() {
        num.value = num.value?.toInt()?.plus(1) ?: 1
    }

    fun isLogin() {
        viewModelScope.launch {
            try {
                println(authRepository.getAuthToken())
            } catch (exception: Exception) {
                println(exception)
            }
        }
    }
}