package com.ltmb.fitness.scene.bookmarkworkoutplan

import android.app.Application
import com.ltmb.fitness.base.BaseAndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkWorkoutPlanViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application)