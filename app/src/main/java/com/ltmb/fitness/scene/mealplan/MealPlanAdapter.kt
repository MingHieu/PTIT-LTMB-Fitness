package com.ltmb.fitness.scene.mealplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseListAdapter
import com.ltmb.fitness.databinding.ItemMealPlanBinding
import com.ltmb.fitness.internal.extension.executeAfter
import com.ltmb.fitness.uimodel.MealPlanUiModel

interface MealPlanCallBack {
    fun onItemClick(mealPlanId: String)
}

class MealPlanAdapter(private val mealPlaneCb: MealPlanCallBack) :
    BaseListAdapter<ItemMealPlanBinding, MealPlanUiModel>() {

    override val layoutRes: Int get() = R.layout.item_meal_plan
    override fun bind(binding: ItemMealPlanBinding, item: MealPlanUiModel) {
        binding.executeAfter {
            callback = mealPlaneCb
            mealPlan = item
        }
    }


}