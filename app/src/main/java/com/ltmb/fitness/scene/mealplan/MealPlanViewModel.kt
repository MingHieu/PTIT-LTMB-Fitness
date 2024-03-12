package com.ltmb.fitness.scene.mealplan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.R
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
                thumbnail = R.drawable.img_meal_sample,
                name = "Meal $i",
                kcal = (i*10).toDouble(),
                type = "Fruit",
            )
            mealPlansList.add(mealPlan)
        }

        _mealPlane.value = mealPlansList
    }

}