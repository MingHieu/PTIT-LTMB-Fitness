package com.ltmb.fitness.scene.mealplan

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.internal.injection.module.NotificationHelper
import com.ltmb.fitness.uimodel.MealPlanUiModel
import javax.inject.Inject

class MealPlanViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {
    private val context: Context = application.applicationContext
    private val _mealPlane = MutableLiveData<List<MealPlanUiModel>>()
    val mealPlans: LiveData<List<MealPlanUiModel>> = _mealPlane

    init {
        val mealPlansList = mutableListOf<MealPlanUiModel>()

        for (i in 1..10) {
            val mealPlan = MealPlanUiModel(
                id = "$i",
                thumbnail = R.drawable.img_meal_sample,
                name = "Meal $i",
                kcal = (i * 10).toDouble(),
                type = "Fruit",
                categorie = "Muscle"
            )
            mealPlansList.add(mealPlan)
        }

        _mealPlane.value = mealPlansList
    }

    fun goToMealPlanDetail() {

        NotificationHelper.showNotification(
            context,
            "Thông báo",
            "Chào mừng bạn đến với ứng dụng của tôi!"
        )
        navigate(MealPlanFragmentDirections.toMealPlanDetailFragment())
    }

}