package com.ltmb.fitness.scene.ranking;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.databinding.FragmentRankingBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RankingFragment extends BaseFragment<RankingViewModel, FragmentRankingBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void initialize() {
        super.initialize();
//        Button button = getView().findViewById(R.id.buttonPlay);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Make Notification
//                Log.d("NOTIFICATION", "MAKE NOTIFICATION");
//            }
//        });
    }
}
