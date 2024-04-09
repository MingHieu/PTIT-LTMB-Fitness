package com.ltmb.fitness.scene.profile;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.databinding.FragmentProfileBinding;
import com.ltmb.fitness.databinding.FragmentRankingBinding;
import com.ltmb.fitness.internal.injection.module.FirebaseFCMModule;
import com.ltmb.fitness.scene.ranking.RankingViewModel;
import com.ltmb.fitness.service.NotificationService;

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
        Log.d("idProfile", id);
        ImageView challenges = this.binding.buttonChallenges;
        challenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFCMModule.sendNotificationFCM(FirebaseFCMModule.getDeviceToken(), "Nguyễn Đức Anh !");
            }
        });
    }
}
