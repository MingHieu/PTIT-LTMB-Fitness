package com.ltmb.fitness.internal.injection.module;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ltmb.fitness.base.NotificationBase;


import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class FirebaseFCMModule extends FirebaseMessagingService {
    private NotificationBase notificationBase = new NotificationBase();
    @Override
    public void onCreate() {
        super.onCreate();
        notificationBase = new NotificationBase();
        notificationBase.createNotificationChannel(this);
    }

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

    public static void sendNotificationFCM(String fcmToken, String title, String body) {
        OkHttpClient client = new OkHttpClient();
        String url = "http://103.95.197.219:8080";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("fcmToken", fcmToken);
            jsonBody.put("title", title);
            jsonBody.put("body", body);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = RequestBody.create(jsonBody.toString(), MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    Log.d("FirebaseFCMModule", responseData);
                } else {
                    Log.d("Response Code: ", String.valueOf(response.code()));
                }
            }
        });
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
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Log.d("FirebaseFCMModule", body);
            Log.d("FirebaseFCMModule", title);
            notificationBase.createNotification(this, title, body);
        }
    }
}
