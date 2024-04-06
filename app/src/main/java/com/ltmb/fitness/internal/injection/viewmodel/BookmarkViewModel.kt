package com.ltmb.fitness.internal.injection.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.WorkoutPlanRepository
import com.ltmb.fitness.uimodel.BookmarkWorkoutPlanUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    application: Application,
    private val workoutPlanRepository: WorkoutPlanRepository
) : BaseAndroidViewModel(application) {

    private val _workoutPlans = MutableLiveData<List<BookmarkWorkoutPlanUiModel>>()
    val workoutPlans: LiveData<List<BookmarkWorkoutPlanUiModel>> = _workoutPlans

    var selecting = MutableLiveData(false)

    private var selected = MutableLiveData(false)

    init {
        getUserBookmarkWorkoutPlanList()
    }

    fun getUserBookmarkWorkoutPlanList() {
        viewModelScope.launch {
            setLoading(true)
            _workoutPlans.value = workoutPlanRepository.getUserBookmarkWorkoutPlanList().reversed()
            setLoading(false)
        }
    }

    fun changeItemSelecting(isSelecting: Boolean) {
        _workoutPlans.value?.let { list ->
            val workoutPlansList = list.map {
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
        viewModelScope.launch {
            val deleteItemIds = _workoutPlans.value!!.filter { it.selected }.map { it.id }
            if (deleteItemIds.isNotEmpty()) {
                setLoading(true)
                workoutPlanRepository.deleteUserBookmarkWorkoutPlanList(deleteItemIds)
                val newItemList = _workoutPlans.value!!.filter { !it.selected }
                _workoutPlans.value = newItemList
                setLoading(false)
            }
            selecting.value = false
        }
    }
}