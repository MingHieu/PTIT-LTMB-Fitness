package com.ltmb.fitness.uimodel

import com.ltmb.fitness.base.ListAdapterItem
import java.io.Serializable

data class MealPlanUiModel(
    override val id: String,
    val thumbnail: String,
    val name: String,
    val kcal: Double,
    val type: String,
    val categorie: String
) : ListAdapterItem, Serializable