package com.ltmb.fitness.scene.home

import androidx.fragment.app.viewModels
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentHomeBinding
import com.ltmb.fitness.internal.injection.viewmodel.BookmarkViewModel
import com.ltmb.fitness.uimodel.BodyAreaUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutId get() = R.layout.fragment_home

    private val bookmarkViewModel by viewModels<BookmarkViewModel>()

    override fun initialize() {
        super.initialize()

        binding.bookmarkViewModel = bookmarkViewModel

        binding.workoutPlanAdapter =
            BookmarkWorkoutPlanAdapter(object : BookmarkWorkoutPlanCallback {
                override fun onItemClick(id: String) {
                    viewModel.onWorkoutPlanClick(id)
                }
            })

        binding.bodyAreaAdapter = BodyAreaAdapter(object : BodyAreaCallback {
            override fun onItemClick(bodyArea: BodyAreaUiModel) {
                viewModel.onBodyAreaItemClick(bodyArea)
            }
        })
    }
}