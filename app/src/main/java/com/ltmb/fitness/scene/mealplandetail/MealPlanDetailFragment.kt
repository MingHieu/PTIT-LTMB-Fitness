package com.ltmb.fitness.scene.mealplandetail

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentMealPlanDetailBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.scene.workoutplandetail.WorkoutAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MealPlanDetailFragment :
    BaseFragment<MealPlanDetailViewModel, FragmentMealPlanDetailBinding>() {
    override val layoutId get() = R.layout.fragment_meal_plan_detail

    private val args by navArgs<MealPlanDetailFragmentArgs>();

    override fun initialize()
    {

        super.initialize()
        viewModel.getMealPlanDetail(args.mealPlanId)
    }
}