package com.ltmb.fitness.uimodel

import com.ltmb.fitness.base.ListAdapterItem
import java.io.Serializable

data class KeySearchUiModel(
    override val id: String,
    val value: String,
    val createdAt: Long,
) : ListAdapterItem, Serializable