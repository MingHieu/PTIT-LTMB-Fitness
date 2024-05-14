package com.ltmb.fitness.data.remote.model.mealdetail

import com.google.firebase.firestore.PropertyName
import com.ltmb.fitness.uimodel.MealPlanDetailUiModel

data class MealDetailModel(
    var id: String = "",
    val thumbnail: String = "",
    val name: String = "",
    val description: String = "",
    val calors: Double = 0.0,
    val sugars: Double = 0.0,
    val protein: Double = 0.0,
    @get:PropertyName("isFavorite")
    val isFavorite: Long = 0,
    val mealsLikeThis: List<MealDetailModel> = listOf(),
) {

    fun toMealDetailUiModel() =
        MealPlanDetailUiModel(
            id = id,
            thumbnail = thumbnail,
            name = name,
            description = description,
            calors = calors,
            sugars = sugars,
            protein = protein,
            isFavorite = isFavorite
        )
}