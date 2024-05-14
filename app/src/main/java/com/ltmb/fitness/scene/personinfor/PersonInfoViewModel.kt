package com.ltmb.fitness.scene.personinfor

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.user.UserModel
import com.ltmb.fitness.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonInfoViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) : BaseAndroidViewModel(application)
{
    private val _userModel  = MutableLiveData<UserModel>()

    val userModel : LiveData<UserModel> = _userModel

//
    init {
//        viewModelScope.launch {
//            val list = userRepository.getUser()
//            _userModel.value = list;
        }
//    println(userModel)

//    }

    fun init() {}
    fun getUser(){
        viewModelScope.launch {
            _userModel.value = userRepository.getUser()
        }
    }
}