package com.ltmb.fitness.uimodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.ltmb.fitness.base.ListAdapterItem
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutes
import java.io.Serializable

data class BookmarkWorkoutPlanUiModel(
    override val id: String,
    val thumbnail: String,
    val name: String,
    val level: String,
    val duration: Long,
    var selecting: Boolean,
    var selected: Boolean,
) : ListAdapterItem, Serializable {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSubTitle(): String = "${convertSecondsToMinutes(duration)} minutes - $level"
}