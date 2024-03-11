package com.ltmb.fitness.scene.mealplan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.MealPlanUiModel
import javax.inject.Inject

class MealPlanViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {
    private val _mealPlane = MutableLiveData<List<MealPlanUiModel>>()
    val mealPlans: LiveData<List<MealPlanUiModel>> = _mealPlane

    init {
        val mealPlansList = mutableListOf<MealPlanUiModel>()

        for (i in 1..10) {
            val mealPlan = MealPlanUiModel(
                id = i.toLong(),
                thumbnail = "https://th.bing.com/th/id/R.5b6b35d25a58124e05fcb4b93664aca8?rik=E9gNJRkp6v6NSg&pid=ImgRaw&r=0",
                name = "Meal $i",
                kcal = (i*10).toDouble(),
                type = "Fruit",
            )
            mealPlansList.add(mealPlan)
        }

        _mealPlane.value = mealPlansList
    }

}