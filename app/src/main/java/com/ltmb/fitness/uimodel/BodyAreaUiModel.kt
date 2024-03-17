package com.ltmb.fitness.uimodel

import androidx.annotation.DrawableRes
import com.ltmb.fitness.base.ListAdapterItem
import java.io.Serializable

data class BodyAreaUiModel(
    override val id: String,
    val name: String,
    @get:DrawableRes
    val drawableRes: Int,
) : ListAdapterItem, Serializable