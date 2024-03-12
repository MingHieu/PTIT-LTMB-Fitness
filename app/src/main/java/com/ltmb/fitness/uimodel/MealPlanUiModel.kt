package com.ltmb.fitness.uimodel

import com.ltmb.fitness.base.ListAdapterItem
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutes
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutesAndSeconds
import java.io.Serializable

data class MealPlanUiModel(
    override val id: Long,
    val thumbnail: Int,
    val name: String,
    val kcal: Double,
    val type: String,
) : ListAdapterItem, Serializable {

}