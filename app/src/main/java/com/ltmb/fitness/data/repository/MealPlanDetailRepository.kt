package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.remote.datasource.MealPlanDetailRemoteDataSource
import com.ltmb.fitness.uimodel.MealPlanDetailUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealPlanDetailRepository @Inject constructor(
    private val remoteDataSource: MealPlanDetailRemoteDataSource
) {
    suspend fun getMealPlanDetail(mealPlanId: String ): MealPlanDetailUiModel {
        var mealPlanDetail = MealPlanDetailUiModel()
        try {
            remoteDataSource.getMealPlanDetail(mealPlanId)?.let {
                mealPlanDetail = it.toMealDetailUiModel()
            }
        } catch (e: Exception) {
            println(e)
        }
        println(mealPlanDetail)
        return mealPlanDetail
    }
}