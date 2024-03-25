package com.ltmb.fitness.scene.selectheight

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.scene.selectheight.SelectHeightFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectHeightViewModel @Inject constructor(application: Application) :
    BaseAndroidViewModel(application) {

        fun onClickContinue(){
            navigate(SelectHeightFragmentDirections.toSelectWeight())
        }

}