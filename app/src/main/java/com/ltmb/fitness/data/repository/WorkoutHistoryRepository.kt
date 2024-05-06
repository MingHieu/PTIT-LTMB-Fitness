package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.remote.datasource.WorkoutHistoryRemoteDataSource
import com.ltmb.fitness.data.remote.model.workouthistory.WorkoutHistoryModel
import com.ltmb.fitness.uimodel.WorkoutHistoryUiModel
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutHistoryRepository @Inject constructor(
    private val remoteDataSource: WorkoutHistoryRemoteDataSource
) {

    suspend fun saveHistory(workoutHistoryUiModel: WorkoutHistoryUiModel): Boolean {
        try {
            return remoteDataSource.saveHistory(workoutHistoryUiModel) != null
        } catch (e: Exception) {
            println(e)
        }
        return false
    }

    suspend fun getWorkoutHistory(startDay: Date, endDay: Date): List<WorkoutHistoryModel> {
        var workoutHistoryList = listOf<WorkoutHistoryModel>()
        try {
            workoutHistoryList = remoteDataSource.getWorkoutHistory(startDay, endDay)
        } catch (e: Exception) {
            println(e)
        }
        return workoutHistoryList
    }
}