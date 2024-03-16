package com.ltmb.fitness.scene.ranking;

import android.app.Application;

import com.ltmb.fitness.base.BaseAndroidViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RankingViewModel extends BaseAndroidViewModel {
    @Inject
    public RankingViewModel(Application application) {
        super(application);
    }
}
