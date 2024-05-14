package com.ltmb.fitness.scene.mealplan

import com.google.android.material.tabs.TabLayout
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentMealPlanBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealPlanFragment : BaseFragment<MealPlanViewModel, FragmentMealPlanBinding>() {
    override val layoutId get() = R.layout.fragment_meal_plan

    override fun initialize() {
        super.initialize()
        binding.mealPlanAdapter = MealPlanAdapter(object : MealPlanCallBack {
            override fun onItemClick(mealPlanId: String) {
                viewModel.goToMealPlanDetail(mealPlanId)
//                TODO("Not yet implemented")
            }
        })

        binding.searchBox.setValue(viewModel.keySearch.value.orEmpty())

        binding.searchBox.onTextChanged = {
            viewModel.keySearch.value = it
        }

        viewModel.keySearch.observeNonNull(viewLifecycleOwner) { searchQuery ->
            viewModel.mealPlans.value?.let { list ->
                viewModel.mealPlansSearch.value = if (searchQuery.isBlank()) {
                    list
                } else {
                    list.filter { it.name.lowercase().contains(searchQuery.lowercase()) }
                }
            }
        }

        binding.mealTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Xử lý khi tab được chọn
                if(tab.position == 0)
                {
                    viewModel.filter("muscle")
                }
                else if(tab.position == 1)
                {
                    viewModel.filter("gain_weight")
                }
                else if(tab.position == 2)
                {
                    viewModel.filter("lose_weight")
                }
                else
                {
                    viewModel.filterFavorite()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Không cần xử lý khi tab không được chọn
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Không cần xử lý khi tab được chọn lại
            }
        })


    }
}