package com.ltmb.fitness.scene.profile;

import android.annotation.SuppressLint;
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
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ltmb.fitness.R;
import com.ltmb.fitness.base.BaseFragment;
import com.ltmb.fitness.data.remote.FirestoreCollections;
import com.ltmb.fitness.data.remote.model.user.UserModel;
import com.ltmb.fitness.databinding.FragmentProfileBinding;
import com.ltmb.fitness.databinding.FragmentRankingBinding;
import com.ltmb.fitness.internal.injection.module.FirebaseFCMModule;
import com.ltmb.fitness.internal.util.CircleTransform;
import com.ltmb.fitness.scene.ranking.RankingViewModel;
import com.ltmb.fitness.service.NotificationService;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> {
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initialize() {
        super.initialize();
        String id = this.fetchId();
        this.getViewModel().setLoading(true);

        // Thread Fetch Data
        new Thread(() -> {
            try {
                UserModel userModel = this.fetchUser(id);
                getActivity().runOnUiThread(() -> {
                    String name = userModel.getFirstName() + " " + userModel.getLastName();
                    binding.profleName.setText((name));
                    binding.profileAge.setText(userModel.getAge() + " ages");
                    binding.profileHeight.setText(userModel.getHeight() + " cm");
                    binding.profileWeight.setText(userModel.getWeight() + " kg");
                    this.uploadAvatar(userModel.getAvatar());
                    this.getViewModel().setLoading(false);
                    this.binding.profileGuiTapLuyen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String title = "Nguyễn Đức Anh đã gửi lời mời luyện tập thách đấu";
                            String body = "Nguyễn Đức Anh đã gửi lời mời luyện tập thách đấu tới bạn !! Hãy vào tập luyện nhé";
                            FirebaseFCMModule.sendNotificationFCM(userModel.getDeviceToken(), title, body);
                        }
                    });
                });
            }
            catch (Exception e) {
                Log.d("FetchProfileFragment: ", Objects.requireNonNull(e.getMessage()));
            }
        }).start();

        ImageView share = this.binding.profileShare;
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://fitness.com/profile/" + id);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }

    private UserModel fetchUser(String id) {
        try {
            CollectionReference users = fireStore.collection(FirestoreCollections.USER);
            DocumentSnapshot documentSnapshot = Tasks.await(users.document(id).get());
            return documentSnapshot.toObject(UserModel.class);
        }
        catch (Exception e) {
            Log.d("FetchProfileFragment: ", Objects.requireNonNull(e.getMessage()));
            return null;
        }
    }

    private String fetchId() {
        Uri uri = this.getActivity().getIntent().getData();
        if (uri != null) {
            return uri.getLastPathSegment();
        }
        else {
            return (String) getArguments().get("idProfile");
        }
    }

    private void uploadAvatar(String avt) {
        Picasso.get()
                .load(avt)
                .transform(new CircleTransform())
                .into(this.binding.profileImage);
    }
}
