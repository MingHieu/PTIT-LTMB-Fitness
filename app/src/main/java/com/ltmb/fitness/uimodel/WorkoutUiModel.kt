package com.ltmb.fitness.uimodel

import com.ltmb.fitness.base.ListAdapterItem
import java.io.Serializable

data class WorkoutUiModel(
    override val id: Long,
    val name: String,
    val thumbnail: String,
    val reps: Long,
    val video: String,
) : ListAdapterItem, Serializable