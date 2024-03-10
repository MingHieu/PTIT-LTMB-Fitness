package com.ltmb.fitness.scene.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.BodyAreaUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _bodyAreas = MutableLiveData<List<BodyAreaUiModel>>()
    val bodyAreas: LiveData<List<BodyAreaUiModel>> = _bodyAreas

    init {
        _bodyAreas.value = listOf(
            BodyAreaUiModel(id = 1, name = "Shoulders", drawableRes = R.drawable.img_shoulder),
            BodyAreaUiModel(id = 2, name = "Chest", drawableRes = R.drawable.img_chest),
            BodyAreaUiModel(id = 3, name = "Arms", drawableRes = R.drawable.img_arm),
            BodyAreaUiModel(id = 4, name = "Stomach", drawableRes = R.drawable.img_stomach),
            BodyAreaUiModel(id = 5, name = "Legs", drawableRes = R.drawable.img_leg)
        )
    }

    fun onSearchBoxClicked() {
        navigate(HomeFragmentDirections.toSearch())
    }

    fun onBodyAreaItemClick() {
        navigate(HomeFragmentDirections.toBodyArea())
    }
}