package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.remote.datasource.WorkoutRemoteDataSource
import com.ltmb.fitness.uimodel.WorkoutSelectionUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutRepository @Inject constructor(
    private val remoteDataSource: WorkoutRemoteDataSource
) {

    suspend fun getWorkoutList(keySearch: String = ""): List<WorkoutSelectionUiModel> {
        var workoutPlanList = listOf<WorkoutSelectionUiModel>()
        try {
            workoutPlanList = remoteDataSource
                .getWorkoutList(keySearch)
                .map { it.toWorkoutSelectionUiModel() }
        } catch (e: Exception) {
            println(e)
        }
        return workoutPlanList
    }
}