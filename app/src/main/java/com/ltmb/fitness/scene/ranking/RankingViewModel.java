package com.ltmb.fitness.scene.ranking;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ltmb.fitness.base.BaseAndroidViewModel;
import com.ltmb.fitness.data.remote.FirestoreCollections;
import com.ltmb.fitness.data.remote.model.user.UserModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RankingViewModel extends BaseAndroidViewModel {
    private final FirebaseFirestore fireStore = FirebaseFirestore.getInstance();

    @Inject
    public RankingViewModel(Application application) {
        super(application);
    }

    public void init() {

    }

    public void navigateToProfile(String id) {
        this.navigate(RankingFragmentDirections.toProfile(id));
    }
}
