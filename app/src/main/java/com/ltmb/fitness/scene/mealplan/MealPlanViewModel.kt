package com.ltmb.fitness.scene.mealplan

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.MealPlanRepository
import com.ltmb.fitness.uimodel.MealPlanUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealPlanViewModel @Inject constructor(
    application: Application,
    private val mealPlanRepository: MealPlanRepository
) : BaseAndroidViewModel(application) {
    private val context: Context = application.applicationContext

    private val _mealPlane = MutableLiveData<List<MealPlanUiModel>>()

    val mealPlans: LiveData<List<MealPlanUiModel>> = _mealPlane

    val mealPlansSearch = MutableLiveData<List<MealPlanUiModel>>()

    val keySearch = MutableLiveData("")

    init {
        viewModelScope.launch {
            val list = mealPlanRepository.filter("muscle")
            _mealPlane.value = list
            mealPlansSearch.value = list
        }
    }

    fun filter(category: String)
    {
        viewModelScope.launch {
            val list = mealPlanRepository.filter(category)
            _mealPlane.value = list
            mealPlansSearch.value = list
        }
    }

    fun filterFavorite()
    {
        viewModelScope.launch {
            val list = mealPlanRepository.filterFavorite()
            _mealPlane.value = list
            mealPlansSearch.value = list
        }
    }

    fun goToMealPlanDetail(mealPlanId: String) {
        navigate(MealPlanFragmentDirections.toMealPlanDetailFragment(mealPlanId))
    }

}