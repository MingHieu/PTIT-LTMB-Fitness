package com.ltmb.fitness.scene.selectgender

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.GenderUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectGenderViewModel @Inject constructor(application: Application) :
    BaseAndroidViewModel(application) {
    val genderSelection = MutableLiveData(GenderUiModel.MALE)
}