package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.remote.datasource.WorkoutPlanRemoteDataSource
import com.ltmb.fitness.data.remote.model.workoutplan.BookmarkWorkoutPlanModel
import com.ltmb.fitness.uimodel.BookmarkWorkoutPlanUiModel
import com.ltmb.fitness.uimodel.SearchUiModel
import com.ltmb.fitness.uimodel.WorkoutPlanDetailUiModel
import com.ltmb.fitness.uimodel.WorkoutPlanUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkoutPlanRepository @Inject constructor(
    private val remoteDataSource: WorkoutPlanRemoteDataSource
) {

    suspend fun searchWorkoutPlan(name: String = "", level: String = ""): List<SearchUiModel> {
        var result = listOf<SearchUiModel>()
        try {
            result = remoteDataSource
                .searchWorkoutPlan(name, level)
                .map { it.toSearchUiModel() }
        } catch (e: Exception) {
            println(e)
        }
        return result
    }

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

    suspend fun getUserBookmarkWorkoutPlanList(): List<BookmarkWorkoutPlanUiModel> {
        var workoutPlanList = listOf<BookmarkWorkoutPlanUiModel>()
        try {
            workoutPlanList = remoteDataSource
                .getUserBookmarkWorkoutPlanList()
                .map { it.toBookmarkWorkoutPlanUiModel() }
        } catch (e: Exception) {
            println(e)
        }
        return workoutPlanList
    }

    suspend fun saveUserBookmarkWorkoutPlan(model: BookmarkWorkoutPlanModel): Boolean {
        try {
            remoteDataSource.saveUserBookmarkWorkoutPlan(model)
            return true
        } catch (e: Exception) {
            println(e)
        }
        return false
    }

    suspend fun deleteUserBookmarkWorkoutPlanList(ids: List<String>): Boolean {
        try {
            remoteDataSource.deleteUserBookmarkWorkoutPlanList(ids)
            return true
        } catch (e: Exception) {
            println(e)
        }
        return false
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