package com.ltmb.fitness.scene.mealplandetail


import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.uimodel.MealPlanDetailUiModel
import javax.inject.Inject


class MealPlanDetailViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {
    private val _mealPlanDetail = MutableLiveData<MealPlanDetailUiModel>()
    val mealPlan: LiveData<MealPlanDetailUiModel>
        get() = _mealPlanDetail

    init {
        val mealPlanDetail = MealPlanDetailUiModel(
            id = "11",
            thumbnail = R.drawable.img_meal_sample,
            name = "Meal Banana",
            description = "Healthy comport food í only a timer away. From low-card soups to healthy meat main dishes. Healthy comport food í only a timer away. From low-card soups to healthy meat main dishes. Healthy comport food í only a timer away. From low-card soups to healthy meat main dishes. Healthy comport food í only a timer away. From low-card soups to healthy meat main dishes.",
            calors = 20.2,
            sugars = 30.2,
            protein = 40.1
        )
        _mealPlanDetail.value = mealPlanDetail
    }

    fun getDrawable(context: Context, drawable: Int): Drawable? {
        return ResourcesCompat.getDrawable(context.resources, drawable, null)
    }
}