package com.ltmb.fitness.scene.workoutdetail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.TutorialType
import com.ltmb.fitness.uimodel.WorkoutPlanDetailUiModel
import com.ltmb.fitness.uimodel.WorkoutUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    var workoutPlanDetail: WorkoutPlanDetailUiModel = WorkoutPlanDetailUiModel()

    private val _workouts = MutableLiveData<List<WorkoutUiModel>>()
    val workouts: LiveData<List<WorkoutUiModel>> = _workouts

    val tutorialType = MutableLiveData(TutorialType.TEXT)

    val current = MutableLiveData(0)

    val paused = MutableLiveData(true)

    val timeLeft = MutableLiveData(0L)

    private var _countDown: Job? = null

    private var totalTime = 0L

    private var totalWorkouts = 0L

    private var totalKcal = 0L

    fun setWorkoutList(list: List<WorkoutUiModel>) {
        _workouts.value = list
        current.value = 0
        updateTimeLeft()
    }

    fun setTutorialType(type: TutorialType) {
        tutorialType.value = type
    }

    fun onPreviousClick() {
        val currentVal = current.value ?: 0
        if (currentVal > 0) {
            current.value = currentVal - 1
            paused.value = true
            updateTimeLeft()
        }
    }

    fun onSkipClick() {
        val currentVal = current.value ?: 0
        if (currentVal < (workouts.value?.size?.minus(1) ?: 0)) {
            current.value = currentVal + 1
            paused.value = true
            updateTimeLeft()
        } else {
            navigate(
                WorkoutDetailFragmentDirections.toWorkoutFinish(
                    workoutPlanDetail,
                    totalWorkouts,
                    totalTime,
                    totalKcal
                )
            )
        }
    }

    private fun updateTimeLeft() {
        val currentVal = current.value ?: 0
        val workoutsVal = workouts.value
        timeLeft.value = workoutsVal?.get(currentVal)?.duration ?: 0
    }

    fun runCountDown() {
        stopCountDown()
        _countDown = viewModelScope.launch {
            while (timeLeft.value!! > 0) {
                if (paused.value == false) {
                    delay(1000)
                    totalTime += 1
                    timeLeft.postValue(timeLeft.value!! - 1)
                }
            }

            if (paused.value == false) {
                totalWorkouts += 1
                current.value?.let {
                    totalKcal += workouts.value?.get(it)?.kcal ?: 0
                }
                onSkipClick()
            }
        }
    }

    fun stopCountDown() {
        _countDown?.cancel()
    }
}