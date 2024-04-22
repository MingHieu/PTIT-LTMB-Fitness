package com.ltmb.fitness.scene.report

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.remote.model.user.UserModel
import com.ltmb.fitness.data.remote.model.workouthistory.WorkoutHistoryModel
import com.ltmb.fitness.data.repository.UserRepository
import com.ltmb.fitness.data.repository.WorkoutHistoryRepository
import com.ltmb.fitness.uimodel.GenderUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import kotlin.time.times

@HiltViewModel
class ReportViewModel @Inject constructor(
    application: Application,
    private val workoutHistoryRepository: WorkoutHistoryRepository,
    private val userRepository: UserRepository
) : BaseAndroidViewModel(application) {

    private val _workoutHistoryList = MutableLiveData<List<WorkoutHistoryModel>>()
    val workoutHistoryList: LiveData<List<WorkoutHistoryModel>> = _workoutHistoryList
    private val _workoutHistory7day = MutableLiveData<List<WorkoutHistoryModel>>()
    val workoutHistory7day: LiveData<List<WorkoutHistoryModel>> = _workoutHistory7day
    val selectReport = MutableLiveData(true)
    val userModel  = MutableLiveData(UserModel())

    private fun setDateTime(date: Date) : Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }

    fun startToday(): Date {
        val calendar = setDateTime(Date())
        return calendar.time
    }
    fun startTomorrow(today: Date): Date {
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
        viewModelScope.launch {
            _workoutHistoryList.value = workoutHistoryRepository.getWorkoutHistory(startDayOfMonth, startDayOfNextMonth)
        }
    }
    fun getWorkoutHistory7day() {
        val today = startToday()
        val tomorrow = startTomorrow(today)
        val calendar= Calendar.getInstance()
        calendar.time = tomorrow
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        val dayPrevious = calendar.time
        println("--------------------ViewModel: ${dayPrevious}")
        println("--------------------ViewModel: ${tomorrow}")
        viewModelScope.launch {
            _workoutHistory7day.value = workoutHistoryRepository.getWorkoutHistory(dayPrevious, tomorrow)
        }
    }
    fun getUser(){
        viewModelScope.launch {
            userModel.value = userRepository.getUser()
        }
    }

    fun calculatorBMI(weight: Double, height: Double): Double {
        return weight / (height * height)
    }
    fun calculateBFP(weight: Double, height: Double, age: Int, gender: GenderUiModel): Double {
        val bmi = calculatorBMI(weight, height)
        val genderFactor = if (gender == GenderUiModel.MALE) 1 else 0
        return  (1.2 * bmi + 0.23 * age - 10.8 * genderFactor - 5.4)
    }

}