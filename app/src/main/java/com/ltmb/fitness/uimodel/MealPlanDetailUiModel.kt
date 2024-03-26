package com.ltmb.fitness.uimodel

import com.ltmb.fitness.base.ListAdapterItem
import java.io.Serializable

data class MealPlanDetailUiModel(
    override val id: String,
    val thumbnail: Int,
    val name: String,
    val description: String,
    val calors: Double,
    val sugars: Double,
    val protein: Double,

    ) : ListAdapterItem, Serializable
