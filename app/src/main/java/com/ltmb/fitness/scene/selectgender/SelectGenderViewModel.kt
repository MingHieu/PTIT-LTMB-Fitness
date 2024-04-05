package com.ltmb.fitness.scene.selectgender

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.UserRepository
import com.ltmb.fitness.uimodel.GenderUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectGenderViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) :
    BaseAndroidViewModel(application) {
    val genderSelection = MutableLiveData(GenderUiModel.MALE)

    fun onClickContinue() {
        viewModelScope.launch {
            if(genderSelection.value == GenderUiModel.MALE){
                userRepository.addGender(true)
            }else{
                userRepository.addGender(false)
            }
            navigate(SelectGenderFragmentDirections.toSelectAge())
        }

    }
}