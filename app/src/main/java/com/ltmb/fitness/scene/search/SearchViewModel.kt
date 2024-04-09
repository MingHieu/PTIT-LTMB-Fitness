package com.ltmb.fitness.scene.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.SearchRepository
import com.ltmb.fitness.data.repository.WorkoutPlanRepository
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
    private val searchRepository: SearchRepository,
    private val workoutPlanRepository: WorkoutPlanRepository
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

    var ignoreFilterFirstTime = true

    var ignoreSearchFirstTime = false

    var instantSearch = false

    val showResult = MutableLiveData(false)

    fun onSearch(text: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (text.isBlank()) {
                _keySearch.value = ""
                showResult.value = false
                _searchResults.value = listOf()
                return@launch
            }
            if (!instantSearch) {
                delay(1000)
            } else {
                instantSearch = false
            }
            _keySearch.value = text
            if (text.isNotEmpty()) {
                setLoading(true)
                saveKeySearchToHistory(text)
                fetchSearchResults(text)
                setLoading(false)
            }

        }
    }

    fun onChangeFilter() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _keySearch.value?.let { text ->
                setLoading(true)
                fetchSearchResults(text)
                setLoading(false)
            }
        }
    }

    private fun saveKeySearchToHistory(text: String) {
        var keySearchList = _keySearchHistory.value!!.toMutableList()
        if (keySearchList.any { keySearch -> keySearch.value == text }) {
            return
        }
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

    private suspend fun fetchSearchResults(text: String) {
        _searchResults.value =
            workoutPlanRepository.searchWorkoutPlan(text, filterSelected.value?.value.orEmpty())
        showResult.value = true
    }

    fun deleteKeySearchHistory(id: String) {
        val newHistory = _keySearchHistory.value!!.filter { it.id != id }
        _keySearchHistory.value = newHistory
        searchRepository.saveKeySearchHistory(newHistory)
    }

    fun setFilterSelected(filter: SearchFilter) {
        _filterSelected.value = filter
    }

    fun goToSearchDetail(id: String) {
        navigate(SearchFragmentDirections.toWorkoutPlanDetail(id))
    }
}