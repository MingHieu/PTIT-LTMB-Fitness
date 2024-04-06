package com.ltmb.fitness.scene.profile;

import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.databinding.FragmentProfileBinding;
import com.ltmb.fitness.databinding.FragmentRankingBinding;
import com.ltmb.fitness.scene.ranking.RankingViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initialize() {
        super.initialize();
        String id = (String) getArguments().get("idProfile");

    }
}
