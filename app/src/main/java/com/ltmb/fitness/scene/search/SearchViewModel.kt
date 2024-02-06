package com.ltmb.fitness.scene.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.ExerciseSearchUiModel
import com.ltmb.fitness.uimodel.SearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _searchResults = MutableLiveData<List<SearchUiModel>>()
    val searchResults: LiveData<List<SearchUiModel>> = _searchResults

    init {
        _searchResults.value = listOf(
            ExerciseSearchUiModel(
                1,
                "Name",
                120,
                "Beginner",
                "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg"
            ),
            ExerciseSearchUiModel(
                2,
                "Name",
                90,
                "Beginner",
                null
            )
        )
    }
}