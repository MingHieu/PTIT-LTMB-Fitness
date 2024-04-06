package com.ltmb.fitness.data.repository

import com.ltmb.fitness.data.local.datasource.SearchLocalDataSource
import com.ltmb.fitness.uimodel.KeySearchUiModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource,
    private val moshi: Moshi
) {

    private val keySearchAdapter: JsonAdapter<List<KeySearchUiModel>> by lazy {
        moshi.adapter<List<KeySearchUiModel>>(List::class.java).nullSafe()
    }

    fun saveKeySearchHistory(keySearchHistory: List<KeySearchUiModel>) {
        val json = keySearchAdapter.toJson(keySearchHistory)
        searchLocalDataSource.setKeySearchListJson(json)
    }

    fun getKeySearchHistory(): List<KeySearchUiModel> {
        val json = searchLocalDataSource.getKeySearchListJson()
        return if (json != null) {
            moshi.adapter(List::class.java).fromJson(json)?.map {
                it as Map<*, *>
                KeySearchUiModel(
                    id = it["id"] as String,
                    value = it["value"] as String,
                    createdAt = (it["createdAt"] as Double).toLong()
                )
            }.orEmpty()
        } else {
            emptyList()
        }
    }
}