package com.ltmb.fitness.scene.profile;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.data.remote.FirestoreCollections;
import com.ltmb.fitness.data.remote.model.user.UserModel;
import com.ltmb.fitness.databinding.FragmentProfileBinding;
import com.ltmb.fitness.databinding.FragmentRankingBinding;
import com.ltmb.fitness.internal.injection.module.FirebaseFCMModule;
import com.ltmb.fitness.scene.ranking.RankingViewModel;
import com.ltmb.fitness.service.NotificationService;

import dagger.hilt.android.AndroidEntryPoint;

interface FetchDataCallback {
    void onCallback(UserModel userModel);
    void onError(Exception e);
}

@AndroidEntryPoint
public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void initialize() {
        super.initialize();
        String deviceToken = FirebaseFCMModule.getDeviceToken();
        Log.d("DeviceToken", deviceToken);
        String id = "";

        // Handle deeplink
        Uri uri = this.getActivity().getIntent().getData();
        if (uri != null) {
            id =  uri.getLastPathSegment();
        }
        // Handle navigate
        else {
            id = (String) getArguments().get("idProfile");
        }

        this.fetchData(id, new FetchDataCallback() {
            @Override
            public void onCallback(UserModel userModel) {
                try {
                    binding.profleName.setText(String.valueOf("Nguyễn Đức Anh"));
                    binding.profileAge.setText(String.valueOf(userModel.getAge()));
                    binding.profileHeight.setText(String.valueOf(userModel.getHeight()));
                    binding.profileWeight.setText(String.valueOf(userModel.getWeight()));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {
                System.out.println("Error fetching data: " + e.getMessage());
            }
        });


        Button challenges = this.binding.profileGuiTapLuyen;
        challenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFCMModule.sendNotificationFCM(FirebaseFCMModule.getDeviceToken(), "Nguyễn Đức Anh !");
            }
        });


        ImageView share = this.binding.profileShare;
        String finalId = id;
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://fitness.com/profile/" + finalId);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }

    private void fetchData(String id, FetchDataCallback callback) {
        FirebaseFirestore.getInstance().collection(FirestoreCollections.USER)
            .document(id)
            .get()
            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            UserModel userModel = document.toObject(UserModel.class);
                            callback.onCallback(userModel);
                        } else {
                            callback.onError(new Exception("No document found"));
                        }
                    } else {
                        callback.onError(task.getException());
                    }
                }
            });
    }
}
