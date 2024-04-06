package com.ltmb.fitness.scene.profile;

import android.app.Application;

import com.ltmb.fitness.base.BaseAndroidViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfileViewModel  extends BaseAndroidViewModel {
    @Inject
    public ProfileViewModel(Application application) {
        super(application);
    }

    public void init() {

    }

    public void navigateToProfile() {
//        navigate(Profi);
    }
}
