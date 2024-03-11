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
    private var selected = MutableLiveData(false)

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
        val workoutPlansList = _bookmarkWorkoutPlans.value!!.map {
            val newItem = it.copy()
            newItem.selecting = isSelecting
            if (!isSelecting) {
                newItem.selected = false
            }
            newItem
        }
        _bookmarkWorkoutPlans.value = workoutPlansList
        if (!isSelecting) {
            selected.value = false
        }
    }

    fun changeItemSelected(id: Long?, isSelected: Boolean) {
        val workoutPlansList = _bookmarkWorkoutPlans.value!!.map {
            val newItem = it.copy()
            if (newItem.id == id || id == null) {
                newItem.selected = isSelected
            }
            newItem
        }
        _bookmarkWorkoutPlans.value = workoutPlansList

        selected.value = _bookmarkWorkoutPlans.value?.find { it.selected } != null
    }

    fun setSelectingValue(value: Boolean) {
        selecting.value = value
    }

    fun deleteItems() {
        val workoutPlansList = _bookmarkWorkoutPlans.value!!.filter { !it.selected }
        _bookmarkWorkoutPlans.value = workoutPlansList
        selecting.value = false
    }

    fun onItemClick() {
        navigate(BookmarkWorkoutPlanFragmentDirections.toWorkoutPlanDetail())
    }
}