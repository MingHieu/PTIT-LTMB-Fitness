package com.ltmb.fitness.scene.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.ExerciseSearchUiModel
import com.ltmb.fitness.uimodel.KeySearchUiModel
import com.ltmb.fitness.uimodel.SearchFilter
import com.ltmb.fitness.uimodel.SearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {

    private val _keySearchHistory = MutableLiveData<List<KeySearchUiModel>>()
    val keySearchHistory: LiveData<List<KeySearchUiModel>> = _keySearchHistory

    private val _keySearch = MutableLiveData("")
    val keySearch: LiveData<String> = _keySearch

    private val _searchResults = MutableLiveData<List<SearchUiModel>>()
    val searchResults: LiveData<List<SearchUiModel>> = _searchResults

    private val _filterSelected = MutableLiveData(SearchFilter.ALL)
    val filterSelected: LiveData<SearchFilter> = _filterSelected

    init {
        _searchResults.value = listOf(
            ExerciseSearchUiModel(
                "1",
                "Name",
                120,
                "Beginner",
                "https://wallpaperbat.com/img/69222-wallpaper-power-pose-back-fitness-gym-image-for-desktop.jpg"
            ),
            ExerciseSearchUiModel(
                "2",
                "Name",
                90,
                "Beginner",
                null
            )
        )

        _keySearchHistory.value = listOf(
            KeySearchUiModel(id = "1", value = "wor"),
            KeySearchUiModel(id = "2", value = "workout"),
            KeySearchUiModel(id = "3", value = "world"),
            KeySearchUiModel(id = "4", value = "word"),
            KeySearchUiModel(id = "5", value = "worry"),
            KeySearchUiModel(id = "6", value = "work"),
            KeySearchUiModel(id = "7", value = "wonder"),
            KeySearchUiModel(id = "8", value = "worth"),
            KeySearchUiModel(id = "9", value = "worn"),
            KeySearchUiModel(id = "10", value = "worm"),
        )
    }

    fun deleteKeySearchHistory(id: String) {
        val newHistory = _keySearchHistory.value!!.filter { it.id != id }
        _keySearchHistory.value = newHistory
    }

    fun setFilterSelected(filter: SearchFilter) {
        _filterSelected.value = filter
    }

    fun onSearch(text: String) {
        println("Search: $text")
        _keySearch.value = text
    }
}