package com.ltmb.fitness.uimodel

import com.google.firebase.firestore.PropertyName
import com.ltmb.fitness.base.ListAdapterItem
import java.io.Serializable

data class MealPlanUiModel(
    override val id: String,
    val thumbnail: String,
    val name: String,
    val kcal: Double,
    val type: String,
    val category: String,
    val isFavorite: Long,
) : ListAdapterItem, Serializable