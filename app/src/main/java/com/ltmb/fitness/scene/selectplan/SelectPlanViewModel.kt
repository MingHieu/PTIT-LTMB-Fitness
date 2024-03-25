package com.ltmb.fitness.scene.selectplan

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.scene.selectplan.SelectPlanFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectPlanViewModel @Inject constructor(application: Application) :
    BaseAndroidViewModel(application) {

        fun onClickContinue(){
            navigate(SelectPlanFragmentDirections.toMain())
        }
}