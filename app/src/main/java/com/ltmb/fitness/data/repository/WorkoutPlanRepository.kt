package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.remote.datasource.WorkoutPlanRemoteDataSource
import com.ltmb.fitness.uimodel.WorkoutPlanDetailUiModel
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutPlanRepository @Inject constructor(
    private val remoteDataSource: WorkoutPlanRemoteDataSource
) {

    suspend fun getWorkoutPlanList(keySearch: String = ""): List<WorkoutPlanUiModel> {
        var workoutPlanList = listOf<WorkoutPlanUiModel>()
        try {
            workoutPlanList = remoteDataSource
                .getWorkoutPlanList(keySearch)
                .map { it.toWorkoutPlanUiModel() }
        } catch (e: Exception) {
            println(e)
        }
        return workoutPlanList
    }

    suspend fun getWorkoutPlanListByBodyAreaId(bodyAreaId: String): List<WorkoutPlanUiModel> {
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

    suspend fun getWorkoutPlanDetail(workoutPlanId: String): WorkoutPlanDetailUiModel {
        var workoutPlanDetail = WorkoutPlanDetailUiModel()
        try {
            remoteDataSource.getWorkoutPlanDetail(workoutPlanId)?.let {
                workoutPlanDetail = it.toWorkoutPlanDetailUiModel()
            }
        } catch (e: Exception) {
            println(e)
        }
        return workoutPlanDetail
    }
}