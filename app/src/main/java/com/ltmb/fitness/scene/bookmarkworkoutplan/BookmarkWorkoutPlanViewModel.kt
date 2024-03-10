package com.ltmb.fitness.scene.bookmarkworkoutplan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.BookmarkWorkoutPlanUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkWorkoutPlanViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _bookmarkWorkoutPlans = MutableLiveData<List<BookmarkWorkoutPlanUiModel>>()
    val bookmarkWorkoutPlans: LiveData<List<BookmarkWorkoutPlanUiModel>> = _bookmarkWorkoutPlans
    var selecting = MutableLiveData(false)
    var selected = MutableLiveData(false)

    init {
        val workoutPlansList = mutableListOf<BookmarkWorkoutPlanUiModel>()

        for (i in 1..10) {
            val workoutPlan = BookmarkWorkoutPlanUiModel(
                id = i.toLong(),
                thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
                name = "Full Body Workout $i",
                level = "Intermediate",
                duration = 60,
                selecting = false,
                selected = false
            )
            workoutPlansList.add(workoutPlan)
        }

        _bookmarkWorkoutPlans.value = workoutPlansList
    }

    fun onAddButtonClick() {}

    fun changeItemSelecting(isSelecting: Boolean) {
        val workoutPlansList = _bookmarkWorkoutPlans.value!!.toMutableList()
        for (i in workoutPlansList.indices) {
            val item = workoutPlansList[i]
            item.selecting = isSelecting
            if (!isSelecting) {
                item.selected = false
            }
        }
        _bookmarkWorkoutPlans.value = workoutPlansList
    }

    fun changeItemSelected(id: Long, isSelected: Boolean) {
        val workoutPlansList = _bookmarkWorkoutPlans.value!!.toMutableList()
        for (i in workoutPlansList.indices) {
            val item = workoutPlansList[i]
            if (item.id == id) {
                item.selected = isSelected
            }
        }
        _bookmarkWorkoutPlans.value = workoutPlansList

        val firstSelectedItem = _bookmarkWorkoutPlans.value?.find { it.selected }
        selected.value = firstSelectedItem != null
    }
}