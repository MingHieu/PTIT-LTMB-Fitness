package com.ltmb.fitness.internal.injection.module;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseFCMModule extends FirebaseMessagingService {

    public static String getDeviceToken() {
        Task<String> task = FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FirebaseFCMModule", "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        Log.d("FirebaseFCMModule", token);
                    }
                });
        // cU7tKg2HSRGOt2k0rbeUJp:APA91bHzolIA0NSeDoa7akDXJgUpQCQTSMQcPJklnv3fzsjjTkuehYi4pqxASuARSeaQ1AECGx-ckv-kNhb6AwiftBUHb3Js75G6t_XYGCEenbQT4M7m1aJodaBR9ULIbiN1raCsVp9A
        return task.getResult();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("FirebaseFCMModule", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("FirebaseFCMModule", "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("FirebaseFCMModule", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}
