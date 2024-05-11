package com.ltmb.fitness.scene.ranking;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.data.remote.FirestoreCollections;
import com.ltmb.fitness.data.remote.model.user.UserModel;
import com.ltmb.fitness.data.remote.model.workouthistory.WorkoutHistoryModel;
import com.ltmb.fitness.databinding.FragmentRankingDailytraninghoursBinding;
import com.ltmb.fitness.internal.util.CircleTransform;
import com.ltmb.fitness.uimodel.RankingPersonUiModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RankingFragmentDailyTrainingHours extends BaseFragment<RankingViewModel, FragmentRankingDailytraninghoursBinding> {
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_ranking_dailytraninghours;
    }

    @Override
    public void initialize() {
        getViewModel().setLoading(true);
        new Thread(() -> {
            try {
                List<RankingPersonUiModel> list = this.fetchData();
                RecyclerRankingAdapter recyclerRankingAdapter = new RecyclerRankingAdapter(list, new RecyclerRankingCallback() {
                    @Override
                    public void onItemClick(String id) {
                        getViewModel().navigateToProfile(id);
                    }
                });

                // Chuyển cập nhật UI về luồng chính
                getActivity().runOnUiThread(() -> {
                    RecyclerView recyclerView = binding.listRankingDailyTrainingHours;
                    recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(recyclerRankingAdapter);
                    this.uploadTopAvatar(list.get(0).getAvt(), list.get(1).getAvt(), list.get(2).getAvt());
                    getViewModel().setLoading(false);
                });
            }
            catch (Exception e) {
                Log.e("Ranking Fragment", e.getMessage());
            }
        }).start();
    }

    public Map<String, UserModel> mapUser() throws ExecutionException, InterruptedException {
        // Get All
        CollectionReference users = fireStore.collection(FirestoreCollections.USER);
        // Map Key -> UserModel
        Map<String, UserModel> map = new HashMap<>();
        // Fetch
        QuerySnapshot snapshot = Tasks.await(users.get());
        for(QueryDocumentSnapshot documentSnapshot: snapshot) {
            UserModel u = documentSnapshot.toObject(UserModel.class);
            map.put(documentSnapshot.getId(), u);
        }
        return map;
    }

    private List<RankingPersonUiModel> fetchData() throws ExecutionException, InterruptedException {
        // Fetch users
        Map<String, UserModel> users = this.mapUser();
        List<RankingPersonUiModel> data = new ArrayList<>();

        // Fetch workout history
        CollectionReference workouts = fireStore.collection(FirestoreCollections.WORKOUT_HISTORY);
        QuerySnapshot snapshot = Tasks.await(workouts.get());

        // Map Id -> RankingPersonUiModel
        Map<String, RankingPersonUiModel> map = new HashMap<>();
        for (QueryDocumentSnapshot document : snapshot) {
            WorkoutHistoryModel workoutHistoryModel = document.toObject(WorkoutHistoryModel.class);
            String id = workoutHistoryModel.getUserId();
            // Find User
            if(users.containsKey(id)) {
                UserModel userModel = users.get(id);
                RankingPersonUiModel rankingPersonUiModel = map.get(id);
                if(rankingPersonUiModel == null) {
                    String name = userModel.getFirstName() + " " + userModel.getLastName();
                    rankingPersonUiModel = new RankingPersonUiModel(id, userModel.getAvatar(), name, 0);
                }
                // Update workoutHistoryModel
                long time = rankingPersonUiModel.getExperience() + workoutHistoryModel.getTimes();
                rankingPersonUiModel.setExperience(time);
                map.put(id, rankingPersonUiModel);
            }
        }

        for(String userId: users.keySet()) {
            RankingPersonUiModel rankingPersonUiModel = map.get(userId);
            if(rankingPersonUiModel == null) {
                UserModel userModel = users.get(userId);
                String name = userModel.getFirstName() + " " + userModel.getLastName();
                rankingPersonUiModel = new RankingPersonUiModel(userId, userModel.getAvatar(), name, 0);
            }
            data.add(rankingPersonUiModel);
        }

        data.sort(new Comparator<RankingPersonUiModel>() {
            @Override
            public int compare(RankingPersonUiModel t1, RankingPersonUiModel t2) {
                return (int) (t2.getExperience() - t1.getExperience());
            }
        });
        return data;
    }

    private void uploadTopAvatar(String avt1, String avt2, String avt3) {
        Picasso.get()
                .load(avt1)
                .transform(new CircleTransform())
                .into(this.binding.avtRankingTop1);

        Picasso.get()
                .load(avt2)
                .transform(new CircleTransform())
                .into(this.binding.avtRankingTop2);

        Picasso.get()
                .load(avt3)
                .transform(new CircleTransform())
                .into(this.binding.avtRankingTop3);
    }
}
