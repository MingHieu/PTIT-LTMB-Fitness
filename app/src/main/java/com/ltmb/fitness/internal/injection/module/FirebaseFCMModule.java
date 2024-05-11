package com.ltmb.fitness.internal.injection.module;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class FirebaseFCMModule extends FirebaseMessagingService {

    public static String getDeviceToken() {
        try {
            return Tasks.await(FirebaseMessaging.getInstance().getToken());
            // cU7tKg2HSRGOt2k0rbeUJp:APA91bHzolIA0NSeDoa7akDXJgUpQCQTSMQcPJklnv3fzsjjTkuehYi4pqxASuARSeaQ1AECGx-ckv-kNhb6AwiftBUHb3Js75G6t_XYGCEenbQT4M7m1aJodaBR9ULIbiN1raCsVp9A
        }
        catch (Exception e) {
            Log.e("GetDeviceToken", Objects.requireNonNull(e.getMessage()));
            return "";
        }
    }

    public static void sendNotificationFCM(String deviceToken, String name) {
//        Notification notification = Notification.builder()
//                .setTitle("PushNotification")
//                .setBody("DucAnh23050")
//                .setImage("https://scontent.fhan15-2.fna.fbcdn.net/v/t1.6435-9/56229242_2791146221110709_8539822219260329984_n.jpg?_nc_cat=103&cb=99be929b-59f725be&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=wec_YRK4baYAX9x932m&_nc_ht=scontent.fhan15-2.fna&oh=00_AfB8GJUuU2KL0xNWJ9EvuZMBJLrIaWa2dZotcHxs1ZyE_g&oe=64F4083A")
//                .build();
//
//        Message message = Message.builder()
//                .setNotification(notification)
//                .putData("score", "850")
//                .putData("time", "2:45")
//                .putData("test", "ducanhdz2002")
//                .setToken(registrationToken)
//                .build();

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("FirebaseFCMModule", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (!remoteMessage.getData().isEmpty()) {
            Log.d("FirebaseFCMModule", "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("FirebaseFCMModule", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}
