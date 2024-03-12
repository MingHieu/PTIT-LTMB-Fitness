package com.ltmb.fitness.scene.ranking;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.databinding.FragmentRankingBinding;
import com.ltmb.fitness.uimodel.RankingPersonUiModel;

import java.util.ArrayList;
import java.util.List;

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
//        List<RankingPersonUiModel> rankingItemList = new ArrayList<>();
//        rankingItemList.add(new RankingPersonUiModel("123", "ducanh", 123));
//        rankingItemList.add(new RankingPersonUiModel("123", "ducanh", 123));
//        rankingItemList.add(new RankingPersonUiModel("123", "ducanh", 123));
//        rankingItemList.add(new RankingPersonUiModel("123", "ducanh", 123));
//        rankingItemList.add(new RankingPersonUiModel("123", "ducanh", 123));
//        rankingItemList.add(new RankingPersonUiModel("123", "ducanh", 123));
//
//        RecyclerRankingAdapter recyclerRankingAdapter = new RecyclerRankingAdapter(rankingItemList);
//        RecyclerView recyclerView = binding.rankingListItem;
//        recyclerView.setAdapter(recyclerRankingAdapter);

        TabLayout tabLayout = binding.rankingTabLayout;
//        ViewPager viewPager = binding.rankingViewPager;
//
//        tabLayout.setupWithViewPager(viewPager);
//
//        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getFragmentManager());
//        vpAdapter.addFragment(new RankingFragmentDailyTrainingHours(), getString(R.string.ranking_tab_icon1));
//        vpAdapter.addFragment(new RankingFragmentWeeklyTrainingDays(), getString(R.string.ranking_tab_icon2));
//        vpAdapter.addFragment(new RankingFragmentTotalTrainingDayd(), getString(R.string.ranking_tab_icon3));
//        viewPager.setAdapter(vpAdapter);

    }
}
