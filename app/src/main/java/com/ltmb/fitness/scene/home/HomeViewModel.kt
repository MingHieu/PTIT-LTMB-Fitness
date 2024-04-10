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
            BodyAreaUiModel(
                id = "shoulder",
                name = "Shoulders",
                drawableRes = R.drawable.img_shoulder
            ),
            BodyAreaUiModel(id = "chest", name = "Chest", drawableRes = R.drawable.img_chest),
            BodyAreaUiModel(id = "arm", name = "Arms", drawableRes = R.drawable.img_arm),
            BodyAreaUiModel(id = "back", name = "Back", drawableRes = R.drawable.img_back),
            BodyAreaUiModel(id = "stomach", name = "Stomach", drawableRes = R.drawable.img_stomach),
            BodyAreaUiModel(id = "leg", name = "Legs", drawableRes = R.drawable.img_leg)
        )
    }

    fun onSearchBoxClicked() {
        navigate(HomeFragmentDirections.toSearch())
    }

    fun onWorkoutPlanClick(id: String) {
        navigate(HomeFragmentDirections.toWorkoutPlanDetail(id))
    }

    fun onViewMoreWorkoutPlanClick() {
        navigate(HomeFragmentDirections.toBookmarkWorkoutPlan())
    }

    fun onBodyAreaItemClick(bodyArea: BodyAreaUiModel) {
        navigate(HomeFragmentDirections.toBodyArea(bodyArea))
    }
}