package com.ltmb.fitness.data.local.datasource

import android.content.SharedPreferences
import com.ltmb.fitness.data.local.delegate.StringPreference
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    private var searchPrefs by StringPreference(sharedPreferences, PREF_SEARCH)

    fun setKeySearchListJson(keySearchList: String) {
        searchPrefs = keySearchList
    }

    fun getKeySearchListJson(): String? = searchPrefs

    companion object {
        private const val PREF_SEARCH = "key_search_set"
    }
}
