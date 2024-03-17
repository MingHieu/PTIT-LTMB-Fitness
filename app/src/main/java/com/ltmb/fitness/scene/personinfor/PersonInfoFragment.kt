package com.ltmb.fitness.scene.personinfor

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentPersonInfoBinding
import com.ltmb.fitness.scene.mealplan.MealPlanAdapter
import com.ltmb.fitness.scene.mealplan.MealPlanCallBack

class PersonInfoFragment : BaseFragment<PersonInfoViewModel, FragmentPersonInfoBinding>(){
    override val layoutId get() = R.layout.fragment_person_info
    override fun initialize() {
        super.initialize()
    }
}