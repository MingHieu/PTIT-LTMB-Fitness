package com.ltmb.fitness.scene.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.SearchRepository
import com.ltmb.fitness.uimodel.ExerciseSearchUiModel
import com.ltmb.fitness.uimodel.KeySearchUiModel
import com.ltmb.fitness.uimodel.SearchFilter
import com.ltmb.fitness.uimodel.SearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application,
    private val searchRepository: SearchRepository
) : BaseAndroidViewModel(application) {

    private val _keySearchHistory =
        MutableLiveData(searchRepository.getKeySearchHistory().sortedByDescending { it.createdAt })
    val keySearchHistory: LiveData<List<KeySearchUiModel>> = _keySearchHistory

    private val _keySearch = MutableLiveData("")
    val keySearch: LiveData<String> = _keySearch

    private val _searchResults = MutableLiveData<List<SearchUiModel>>()
    val searchResults: LiveData<List<SearchUiModel>> = _searchResults

    private val _filterSelected = MutableLiveData(SearchFilter.ALL)
    val filterSelected: LiveData<SearchFilter> = _filterSelected

    private var searchJob: Job? = null

    fun onSearch(text: String) {
        _keySearch.value = text
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            if (text.isNotEmpty()) {
                setLoading(true)
                delay(500)
                saveKeySearchToHistory(text)
                fetchSearchResults(text)
                setLoading(false)
            }
        }
    }

    private fun saveKeySearchToHistory(text: String) {
        var keySearchList = _keySearchHistory.value!!.toMutableList()
        keySearchList.add(
            KeySearchUiModel(
                id = "${Date().time}",
                value = text,
                createdAt = Date().time
            )
        )
        keySearchList = keySearchList.sortedByDescending { it.createdAt }.toMutableList()
        _keySearchHistory.value = keySearchList
        searchRepository.saveKeySearchHistory(keySearchList)
    }

    private fun fetchSearchResults(text: String) {
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
    }

    fun deleteKeySearchHistory(id: String) {
        val newHistory = _keySearchHistory.value!!.filter { it.id != id }
        _keySearchHistory.value = newHistory
        searchRepository.saveKeySearchHistory(newHistory)
    }

    fun setFilterSelected(filter: SearchFilter) {
        _filterSelected.value = filter
    }
}