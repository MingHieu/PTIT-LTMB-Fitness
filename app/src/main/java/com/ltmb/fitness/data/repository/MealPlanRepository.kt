package com.ltmb.fitness.data.repository;


import com.ltmb.fitness.data.remote.datasource.MealPlanRemoteDataSource;
import com.ltmb.fitness.uimodel.MealPlanUiModel;
import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
class MealPlanRepository @Inject constructor(
    private val remoteDataSource:MealPlanRemoteDataSource
) {

    suspend fun getMealPlanList(keySearch: String = ""): List<MealPlanUiModel> {
        var mealPlanList = listOf<MealPlanUiModel>()
        try {
            mealPlanList = remoteDataSource
                .getMealPlanList(keySearch)
                .map { it.toMealUiModel() }
        } catch (e: Exception) {
            println(e)
        }
        return mealPlanList
    }

    suspend fun filter(category: String): List<MealPlanUiModel> {
        var mealPlanList = listOf<MealPlanUiModel>()
        try {
            mealPlanList = remoteDataSource
                .filter(category)
                .map { it.toMealUiModel() }
        } catch (e: Exception) {
            println(e)
        }
        return mealPlanList
    }

    suspend fun filterFavorite(): List<MealPlanUiModel> {
        var mealPlanList = listOf<MealPlanUiModel>()
        try {
            mealPlanList = remoteDataSource
                .filterFavorite()
                .map { it.toMealUiModel() }
        } catch (e: Exception) {
            println(e)
        }
        return mealPlanList
    }
}
