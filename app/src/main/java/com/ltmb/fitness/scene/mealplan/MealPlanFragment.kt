package com.ltmb.fitness.scene.mealplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentMealPlanBinding
import com.ltmb.fitness.scene.workoutplan.WorkoutPlanAdapter
import com.ltmb.fitness.scene.workoutplan.WorkoutPlanCallback
import com.ltmb.fitness.scene.workoutplandetail.WorkoutAdapter
import com.ltmb.fitness.scene.workoutplandetail.WorkoutCallback

class MealPlanFragment : BaseFragment <MealPlanViewModel, FragmentMealPlanBinding>() {
    override val layoutId get() = R.layout.fragment_meal_plan

    override fun initialize() {
        super.initialize()
        binding.mealPlanAdapter = MealPlanAdapter(object : MealPlanCallBack {
            override fun onItemClick() {
                TODO("Not yet implemented")
            }
        })
    }
}