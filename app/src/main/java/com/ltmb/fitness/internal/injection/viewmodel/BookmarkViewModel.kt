package com.ltmb.fitness.internal.injection.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.BookmarkWorkoutPlanUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _workoutPlans = MutableLiveData<List<BookmarkWorkoutPlanUiModel>>()
    val workoutPlans: LiveData<List<BookmarkWorkoutPlanUiModel>> = _workoutPlans

    var selecting = MutableLiveData(false)

    private var selected = MutableLiveData(false)

    init {
        val workoutPlansList = mutableListOf<BookmarkWorkoutPlanUiModel>()

        for (i in 1..10) {
            val workoutPlan = BookmarkWorkoutPlanUiModel(
                id = "$i",
                thumbnail = "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg",
                name = "Full Body Workout $i",
                level = "Intermediate",
                duration = 60,
                selecting = false,
                selected = false
            )
            workoutPlansList.add(workoutPlan)
        }

        _workoutPlans.value = workoutPlansList
    }

    fun changeItemSelecting(isSelecting: Boolean) {
        val workoutPlansList = _workoutPlans.value!!.map {
            val newItem = it.copy()
            newItem.selecting = isSelecting
            if (!isSelecting) {
                newItem.selected = false
            }
            newItem
        }
        _workoutPlans.value = workoutPlansList
        if (!isSelecting) {
            selected.value = false
        }
    }

    fun changeItemSelected(id: String, isSelected: Boolean) {
        val workoutPlansList = _workoutPlans.value!!.map {
            val newItem = it.copy()
            if (newItem.id == id) {
                newItem.selected = isSelected
            }
            newItem
        }
        _workoutPlans.value = workoutPlansList

        selected.value = _workoutPlans.value?.find { it.selected } != null
    }

    fun setSelectingValue(value: Boolean) {
        selecting.value = value
    }

    fun deleteItems() {
        val workoutPlansList = _workoutPlans.value!!.filter { !it.selected }
        _workoutPlans.value = workoutPlansList
        selecting.value = false
    }
}