package com.ltmb.fitness.data.remote.model.meal

import com.ltmb.fitness.uimodel.MealPlanUiModel
import com.ltmb.fitness.uimodel.WorkoutUiModel

data class MealModel(
    var id: String = "",
    val thumbnail: String = "",
    val name: String = "",
    val kcal: Double = 0.0,
    val type: String = "",
    val categorie: String = "")
{
    fun toMealUiModel() =
        MealPlanUiModel(
            id = id,
            thumbnail = thumbnail,
            name = name,
            kcal = kcal,
            type = type,
            categorie = categorie
        )
}


//package com.ltmb.fitness.uimodel
//
//import com.ltmb.fitness.base.ListAdapterItem
//import java.io.Serializable
//
//data class MealPlanUiModel(
//    override val id: String,
//    val thumbnail: Int,
//    val name: String,
//    val kcal: Double,
//    val type: String,
//    val categorie: String
//) : ListAdapterItem, Serializable