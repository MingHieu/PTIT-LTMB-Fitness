package com.ltmb.fitness.scene.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.BodyAreaUiModel
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _workoutPlans = MutableLiveData<List<WorkoutPlanUiModel>>()
    val workoutPlans: LiveData<List<WorkoutPlanUiModel>> = _workoutPlans

    private val _bodyAreas = MutableLiveData<List<BodyAreaUiModel>>()
    val bodyAreas: LiveData<List<BodyAreaUiModel>> = _bodyAreas


    init {
        val workoutPlansList = mutableListOf<WorkoutPlanUiModel>()

        for (i in 1..2) {
            val workoutPlan = WorkoutPlanUiModel(
                id = "$i",
                thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
                name = "Full Body Workout $i",
                level = "Intermediate",
                duration = 60,
            )
            workoutPlansList.add(workoutPlan)
        }

        _workoutPlans.value = workoutPlansList

        _bodyAreas.value = listOf(
            BodyAreaUiModel(id = "1", name = "Shoulders", drawableRes = R.drawable.img_shoulder),
            BodyAreaUiModel(id = "2", name = "Chest", drawableRes = R.drawable.img_chest),
            BodyAreaUiModel(id = "3", name = "Arms", drawableRes = R.drawable.img_arm),
            BodyAreaUiModel(id = "4", name = "Back", drawableRes = R.drawable.img_back),
            BodyAreaUiModel(id = "5", name = "Stomach", drawableRes = R.drawable.img_stomach),
            BodyAreaUiModel(id = "6", name = "Legs", drawableRes = R.drawable.img_leg)
        )
    }

    fun onSearchBoxClicked() {
        navigate(HomeFragmentDirections.toSearch())
    }

    fun onWorkoutPlanClick() {
        navigate(HomeFragmentDirections.toWorkoutPlanDetail())
    }

    fun onViewMoreWorkoutPlanClick() {
        navigate(HomeFragmentDirections.toBookmarkWorkoutPlan())
    }

    fun onBodyAreaItemClick(bodyArea: BodyAreaUiModel) {
        navigate(HomeFragmentDirections.toBodyArea(bodyArea))
    }
}