package com.ltmb.fitness.scene.setting

import android.R
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.AuthRepository
import com.ltmb.fitness.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : BaseAndroidViewModel(application) {

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

     fun sendEmail(context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            setLoading(true)
            delay(2000) // "Ngủ" trong 2 giây trước khi gửi email
            setLoading(false)
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("hongduy11092002@gmail.com"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Yêu cầu hỗ trợ")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Xin chào. Tôi muốn giúp đỡ")
            if (emailIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(Intent.createChooser(emailIntent, "Send email..."))
                // Hiển thị popup thông báo khi gửi email thành công
            } else {
                // Hiển thị thông báo khi không tìm thấy ứng dụng email
                Toast.makeText(context, "No email app found!", Toast.LENGTH_SHORT).show()
            }
        }
         Toast.makeText(context, "Send email...", Toast.LENGTH_LONG).show()


    }




}