package com.ltmb.fitness.scene.mealplandetail


import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ltmb.fitness.base.BaseAndroidViewModel
import com.ltmb.fitness.data.repository.MealPlanDetailRepository
import com.ltmb.fitness.data.repository.WorkoutPlanRepository
import com.ltmb.fitness.uimodel.MealPlanDetailUiModel
import com.ltmb.fitness.uimodel.MealPlanUiModel
import com.ltmb.fitness.uimodel.WorkoutUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.IOException
import java.net.URL
import javax.inject.Inject


@HiltViewModel
class MealPlanDetailViewModel @Inject constructor(
    application: Application,
    private val mealPlanDetailRepository: MealPlanDetailRepository
) : BaseAndroidViewModel(application) {
    private val _mealPlanDetail = MutableLiveData<MealPlanDetailUiModel>()
    val mealPlan: LiveData<MealPlanDetailUiModel>
        get() = _mealPlanDetail

    private val _mealFavorite = MutableLiveData<List<MealPlanUiModel>>()
    val mealFavorite: LiveData<List<MealPlanUiModel>> = _mealFavorite

//    init {
//        val mealPlanDetail = MealPlanDetailUiModel(
//            id = "11",
//            thumbnail = "https://cleverads.vn/blog/wp-content/uploads/2023/10/thi-truong-healthy-food-1-768x513.jpg",
//            name = "Meal Banana",
//            description = "Healthy comport food í only a timer away. From low-card soups to healthy meat main dishes. Healthy comport food í only a timer away. From low-card soups to healthy meat main dishes. Healthy comport food í only a timer away. From low-card soups to healthy meat main dishes. Healthy comport food í only a timer away. From low-card soups to healthy meat main dishes.",
//            calors = 20.2,
//            sugars = 30.2,
//            protein = 40.1,
//            isFavorite = 0
//        )
//        _mealPlanDetail.value = mealPlanDetail
//    }


    fun getMealPlanDetail(id: String) {
        viewModelScope.launch {
            setLoading(true)
            _mealPlanDetail.value = mealPlanDetailRepository.getMealPlanDetail(id)
            _mealFavorite.value = _mealPlanDetail.value?.mealLikeThis
            setLoading(false)
        }
    }

    fun onBookmarkClick(){

    }

}