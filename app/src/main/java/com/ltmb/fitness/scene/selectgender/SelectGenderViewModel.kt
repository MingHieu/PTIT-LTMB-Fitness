package com.ltmb.fitness.scene.selectgender

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.user.UserModel
import com.ltmb.fitness.data.repository.UserRepository
import com.ltmb.fitness.uimodel.GenderUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectGenderViewModel @Inject constructor(
    application: Application,
) :
    BaseAndroidViewModel(application) {
    val genderSelection = MutableLiveData(GenderUiModel.MALE)

    fun onClickContinue() {
        navigate(SelectGenderFragmentDirections.toSelectAge())
    }
    fun onClickSkip(){
        navigate(SelectGenderFragmentDirections.toHome())
    }
}