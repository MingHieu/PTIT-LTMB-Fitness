package com.ltmb.fitness.uimodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.ltmb.fitness.base.ListAdapterItem
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutesAndSeconds
import java.io.Serializable

sealed class SearchUiModel : ListAdapterItem, Serializable {
    abstract fun getTitle(): String
    abstract fun getSubTitle(): String
    open fun getImagePath(): String = "https://placehold.co/100x100"
}

enum class SearchFilter(val value: String) {
    ALL(""),
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced")
}

data class ExerciseSearchUiModel(
    override val id: String,
    val name: String,
    val duration: Long,
    val level: String,
    val thumbnail: String?
) : SearchUiModel() {
    override fun getTitle(): String = name

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getSubTitle(): String = "${convertSecondsToMinutesAndSeconds(duration)} - $level"

    override fun getImagePath(): String = thumbnail ?: super.getImagePath()
}