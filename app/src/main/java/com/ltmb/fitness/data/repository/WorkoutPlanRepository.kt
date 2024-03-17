package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.remote.datasource.WorkoutPlanRemoteDataSource
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutPlanRepository @Inject constructor(
    private val remoteDataSource: WorkoutPlanRemoteDataSource
) {

    suspend fun getWorkoutPlanList() = remoteDataSource.getWorkoutPlanList()

    suspend fun getWorkoutPlanList(bodyAreaId: String): List<WorkoutPlanUiModel> {
        var workoutPlanList = listOf<WorkoutPlanUiModel>()
        try {
            workoutPlanList = remoteDataSource
                .getWorkoutPlanListByBodyArea(bodyAreaId)
                .map { it.toWorkoutPlanUiModel() }
        } catch (e: Exception) {
            println(e)
        }
        return workoutPlanList
    }
}