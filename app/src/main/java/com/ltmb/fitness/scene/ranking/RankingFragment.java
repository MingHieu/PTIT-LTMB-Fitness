package com.ltmb.fitness.scene.ranking;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.databinding.FragmentRankingBinding;

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

        TabLayout tabLayout = binding.rankingTabLayout;
        ViewPager viewPager = binding.rankingViewPager;

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getFragmentManager());
        List<Fragment> listFragmentTab = new ArrayList<>();
        listFragmentTab.add(new RankingFragmentDailyTrainingHours());
        listFragmentTab.add(new RankingFragmentDailyTrainingHours());
        listFragmentTab.add(new RankingFragmentDailyTrainingHours());
//        listFragmentTab.add(new RankingFragmentWeeklyTrainingDays());
//        listFragmentTab.add(new RankingFragmentTotalTrainingDayd());

        vpAdapter.addFragment(listFragmentTab.get(0), getString(R.string.ranking_tab_icon1));
        vpAdapter.addFragment(listFragmentTab.get(1), getString(R.string.ranking_tab_icon2));
        vpAdapter.addFragment(listFragmentTab.get(2), getString(R.string.ranking_tab_icon3));

        viewPager.setAdapter(vpAdapter);
        tabLayout.setupWithViewPager(viewPager);
        this.setUpTabLayoutIcon(tabLayout);
    }

    private void setUpTabLayoutIcon(@NonNull TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_items_clock);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_items_calendar);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_items_dumbbell);
    }
}
