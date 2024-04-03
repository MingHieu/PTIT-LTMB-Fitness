package com.ltmb.fitness.uimodel

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.ltmb.fitness.base.ListAdapterItem
import com.ltmb.fitness.internal.util.functional.convertSecondsToMinutesAndSeconds
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class WorkoutUiModel(
    override val id: String,
    val thumbnail: String,
    val name: String,
    val duration: Long,
    val kcal: Long,
    val video: String,
    val tutorialText: String,
) : ListAdapterItem, Serializable, Parcelable {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDurationInMinutesAndSeconds(): String = convertSecondsToMinutesAndSeconds(duration)

    fun getTutorialTextFormat() = tutorialText.replace("\\n", System.lineSeparator())
}