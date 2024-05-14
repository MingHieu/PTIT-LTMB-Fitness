package com.ltmb.fitness.data.remote.model.meal

import com.google.firebase.firestore.PropertyName
import com.ltmb.fitness.uimodel.MealPlanUiModel

data class MealModel(
    var id: String = "",
    val thumbnail: String = "",
    val name: String = "",
    val kcal: Double = 0.0,
    val type: String = "",
    val category: String = "",
    @get:PropertyName("isFavorite")
    val isFavorite: Long = 0)
{
    fun toMealUiModel() =
        MealPlanUiModel(
            id = id,
            thumbnail = thumbnail,
            name = name,
            kcal = kcal,
            type = type,
            category = category,
            isFavorite = isFavorite
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