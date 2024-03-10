package com.ltmb.fitness.scene.bookmarkworkoutplan

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentBookmarkWorkoutPlanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkWorkoutPlanFragment :
    BaseFragment<BookmarkWorkoutPlanViewModel, FragmentBookmarkWorkoutPlanBinding>() {
    override val layoutId get() = R.layout.fragment_bookmark_workout_plan

}