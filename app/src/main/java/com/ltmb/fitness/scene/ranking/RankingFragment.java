package com.ltmb.fitness.scene.ranking;

import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.boardcast.MyBroadcastReceiver;
import com.ltmb.fitness.databinding.FragmentRankingBinding;
import com.ltmb.fitness.internal.injection.module.FirebaseFCMModule;
import com.ltmb.fitness.service.ScheduleService;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RankingFragment extends BaseFragment<RankingViewModel, FragmentRankingBinding> {
    private ScheduleService scheduleService;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void initialize() {
        super.initialize();
        this.scheduleService = new ScheduleService(this.getContext());

        TabLayout tabLayout = binding.rankingTabLayout;
        ViewPager2 viewPager = binding.rankingViewPager;

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getActivity());
        List<Fragment> listFragmentTab = new ArrayList<>();
        listFragmentTab.add(new RankingFragmentDailyTrainingHours());
        listFragmentTab.add(new RankingFragmentDailyTrainingHours());
        listFragmentTab.add(new RankingFragmentDailyTrainingHours());

        vpAdapter.addFragment(listFragmentTab.get(0), getString(R.string.ranking_tab_icon1));
        vpAdapter.addFragment(listFragmentTab.get(1), getString(R.string.ranking_tab_icon2));
        vpAdapter.addFragment(listFragmentTab.get(2), getString(R.string.ranking_tab_icon3));

        viewPager.setAdapter(vpAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    tab.setText(vpAdapter.getTitle(position));
                }
        ).attach();
        this.setUpTabLayoutIcon(tabLayout);

        String fcmToken = FirebaseFCMModule.getDeviceToken();

//        this.scheduleService.setRemindWork(System.currentTimeMillis() + 10 * 1000);
//        Log.d("ScheduleService", "123");

        Intent intent = new Intent(this.getContext(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

//        AlarmManager alarmManager = (AlarmManager) this.getContext().getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10 * 1000, pendingIntent);
        this.scheduleService.setRemindWork(System.currentTimeMillis() + 10 * 1000);

    }

    private void setUpTabLayoutIcon(@NonNull TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_items_clock);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_items_calendar);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_items_dumbbell);
    }
}
