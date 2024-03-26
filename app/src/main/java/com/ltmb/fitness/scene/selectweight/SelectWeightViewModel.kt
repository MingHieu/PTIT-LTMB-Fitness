package com.ltmb.fitness.scene.selectweight

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.scene.selectweight.SelectWeightFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectWeightViewModel @Inject constructor(application: Application) :
    BaseAndroidViewModel(application) {

        fun onClickContinue(){
            navigate(SelectWeightFragmentDirections.toSelectPlan())
        }
}