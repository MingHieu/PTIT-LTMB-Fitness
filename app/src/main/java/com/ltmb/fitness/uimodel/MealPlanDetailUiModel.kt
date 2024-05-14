package com.ltmb.fitness.uimodel

import android.os.Parcelable
import com.ltmb.fitness.base.ListAdapterItem
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class MealPlanDetailUiModel(
    override val id: String = "",
    val thumbnail: String = "",
    val name: String = "",
    val description: String ="",
    val calors: Double = 0.0,
    val sugars: Double = 0.0,
    val protein: Double = 0.0,
    val isFavorite: Long = 0,
    val mealLikeThis: List<MealPlanUiModel> = listOf(),

    ) : ListAdapterItem, Serializable, Parcelable
