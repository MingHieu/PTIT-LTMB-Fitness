package com.ltmb.fitness.scene.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.ExerciseSearchUiModel
import com.ltmb.fitness.uimodel.SearchFilter
import com.ltmb.fitness.uimodel.SearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _keySearch = MutableLiveData("")
    val keySearch: LiveData<String> = _keySearch

    private val _searchResults = MutableLiveData<List<SearchUiModel>>()
    val searchResults: LiveData<List<SearchUiModel>> = _searchResults

    private val _filterSelected = MutableLiveData(SearchFilter.ALL)
    val filterSelected: LiveData<SearchFilter> = _filterSelected

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

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

    fun setFilterSelected(filter: SearchFilter) {
        _filterSelected.value = filter
    }

    fun onSearch(text: String) {
        println("Search: $text")
        _keySearch.value = text
    }
}