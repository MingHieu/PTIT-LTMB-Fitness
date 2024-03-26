package com.ltmb.fitness.scene.mealplandetail

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentMealPlanDetailBinding
import com.ltmb.fitness.scene.mealplan.MealPlanAdapter
import com.ltmb.fitness.scene.mealplan.MealPlanCallBack


class MealPlanDetailFragment :BaseFragment<MealPlanDetailViewModel, FragmentMealPlanDetailBinding>() {
    override val layoutId get() = R.layout.fragment_meal_plan_detail

    override fun initialize() {
        super.initialize()
    }
}