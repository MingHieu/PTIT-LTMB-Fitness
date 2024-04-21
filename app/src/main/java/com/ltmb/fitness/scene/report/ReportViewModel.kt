package com.ltmb.fitness.scene.report

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.workouthistory.WorkoutHistoryModel
import com.ltmb.fitness.data.repository.WorkoutHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor(
    application: Application,
    private val workoutHistoryRepository: WorkoutHistoryRepository
) : BaseAndroidViewModel(application) {

    private val _workoutHistoryList = MutableLiveData<List<WorkoutHistoryModel>>()
    val workoutHistoryList: LiveData<List<WorkoutHistoryModel>> = _workoutHistoryList
    val selectReport = MutableLiveData(true)

    private fun setDateTime(date: Date) : Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }

    private fun startToday(): Date {
        val calendar = setDateTime(Date())
        return calendar.time
    }
    private fun startTomorrow(today: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = today
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        return calendar.time
    }
    private fun startDayOfMonth(): Date {
        val calendar = setDateTime(Date())
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar.time
    }

    private fun startDayOfNextMonth(startDayOfMonth: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = startDayOfMonth
        calendar.add(Calendar.MONTH, 1)
        return calendar.time
    }


    fun getWorkoutHistoryToday() {
        val today = startToday()
        val tomorrow = startTomorrow(today)
        viewModelScope.launch {
            _workoutHistoryList.value = workoutHistoryRepository.getWorkoutHistory(today, tomorrow)
        }
    }

    fun getWorkoutHistoryMonth() {
        val startDayOfMonth = startDayOfMonth()
        val startDayOfNextMonth = startDayOfNextMonth(startDayOfMonth)
        println("--------------------ViewModel: ${startDayOfMonth}")
        println("--------------------ViewModel: ${startDayOfNextMonth}")
    }

}