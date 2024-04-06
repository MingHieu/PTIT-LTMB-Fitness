package com.ltmb.fitness.scene.ranking;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.databinding.FragmentRankingDailytraninghoursBinding;
import com.ltmb.fitness.internal.util.CircleTransform;
import com.ltmb.fitness.uimodel.RankingPersonUiModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RankingFragmentDailyTrainingHours extends BaseFragment<RankingViewModel, FragmentRankingDailytraninghoursBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking_dailytraninghours;
    }

    @Override
    public void initialize() {

        RecyclerRankingAdapter recyclerRankingAdapter = new RecyclerRankingAdapter(fetchData(), new RecyclerRankingCallback() {
            @Override
            public void onItemClick(String id) {
                Log.d("Personal Profile Id", id);
                getViewModel().navigateToProfile(id);
            }
        });
        RecyclerView recyclerView = binding.listRankingDailyTrainingHours;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(recyclerRankingAdapter);
    }

    private List<RankingPersonUiModel> fetchData() {
        String urlAvt1 = "https://scontent.fhan15-2.fna.fbcdn.net/v/t39.30808-6/414926233_4356201574605158_2142184248890356244_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeGNP5lUjCrq349Te6Zqiesn77kriFuXyf7vuSuIW5fJ_ixCuTsl1TmFg5Jzn6RQVRO7bQS_dEs1r8XwgXaP6kan&_nc_ohc=KStj93fj1ZUAX9_yO-w&_nc_ht=scontent.fhan15-2.fna&oh=00_AfBgZw0WLO3ccIMUYAKYyBhI-z4CU5R9vA-6nWSt9vRILg&oe=65FA9098";
        String urlAvt2 = "https://scontent.fhan5-6.fna.fbcdn.net/v/t1.6435-9/102444094_2680182698969893_8249210568847174462_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeEpyWFN1aHT1Q45FPGkchUpqtFTrNTNgcKq0VOs1M2BwrRXL6vTZUMtGoOTMfNzdpvj3s1BoHA-X340WCbZDB5q&_nc_ohc=1_t04TVkCjoAX8DO_ta&_nc_ht=scontent.fhan5-6.fna&oh=00_AfBQKgErp6yj_sU2c4zZFWW1XLy8W4_V4O-8NnITKTe3eg&oe=661D0BF4";
        String urlAvt3 = "https://scontent.fsgn5-5.fna.fbcdn.net/v/t39.30808-6/272434633_3162373927417432_3106694300020144035_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeFCtXM9Q1a4jnvjwgyakiTMZ2MnzryXhPtnYyfOvJeE-28WjXn7MzE5F9BZiPb1gUMuf_mtMxsUM51TOc4MfidX&_nc_ohc=BDqPTFyU3OMAX8onvBX&_nc_ht=scontent.fsgn5-5.fna&oh=00_AfDUiTaDP370tDE-l5ffezNJXCVR-i6fijkJdpMS62Hh0A&oe=65FB7C67";
        String urlAvt4 = "https://scontent.fhan5-9.fna.fbcdn.net/v/t39.30808-6/358154656_4220490354842948_3086160310882159875_n.jpg?stp=cp6_dst-jpg&_nc_cat=109&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeGF7pbQOjp8Sx0dZt1x45xB-3r1qwswL_z7evWrCzAv_BVbxyL9-6L_R2wRtCjLc0lpTl9OWez32JxcHHTHKYXA&_nc_ohc=7OEngh9D5HkAX_-pQKC&_nc_ht=scontent.fhan5-9.fna&oh=00_AfBr6bc9eXI32hwCtievpZGrFPP3xHdr0vceYfXEZfZ0HA&oe=65FAED2F";
        String urlAvt5 = "https://scontent.fhan15-2.fna.fbcdn.net/v/t1.6435-9/123082932_3370335039858488_567240001107576423_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=5f2048&_nc_eui2=AeHatGevnIYrlVfAxRXRrHpoHyNAx2OGL50fI0DHY4YvnZ7xKpJ3Rh3o8XeTfrKaY3htKm_1xc16EKRryNji-09X&_nc_ohc=G3EeOM2rddwAX-BUCyk&_nc_ht=scontent.fhan15-2.fna&oh=00_AfDoh-O1DGLKfkDfGFv5dOX6pi286376CVFHqspeRjZ0KA&oe=661D01CF";

        List<RankingPersonUiModel> data = new ArrayList<>();
        data.add(new RankingPersonUiModel(urlAvt1, "Nguyễn Đức Anh", 332));
        data.add(new RankingPersonUiModel(urlAvt2, "Lê Hồng Duy", 332));
        data.add(new RankingPersonUiModel(urlAvt3, "Lê Hồng Duy", 196));
        data.add(new RankingPersonUiModel(urlAvt4, "Nguyễn Đức Anh", 185));
        data.add(new RankingPersonUiModel(urlAvt5, "Nguyễn Đức Anh", 172));

        Picasso.get()
                .load(urlAvt1)
                .transform(new CircleTransform())
                .into(this.binding.avtRankingTop1);

        Picasso.get()
                .load(urlAvt2)
                .transform(new CircleTransform())
                .into(this.binding.avtRankingTop2);

        Picasso.get()
                .load(urlAvt3)
                .transform(new CircleTransform())
                .into(this.binding.avtRankingTop3);

        return data;
    }
}
