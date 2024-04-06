package com.ltmb.fitness.service;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.ltmb.fitness.R;

import java.util.Date;

public class NotificationService {
    private final int NOTIFICATION_CODE = 100;
    private final Context context;

    public NotificationService(Context context) {
        this.context = context;
    }

    private static void createNotification(Context context, final String CHANNEL_ID, final String title, final String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent intent = new Intent(context, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.raw.animation_firework);
        builder.setLargeIcon(largeIcon);

        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.music_notification);
        mediaPlayer.start();

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.notify((int) new Date().getTime(), builder.build());
    }

    private void createNotificationChannel(Context context, final String CHANNEL_ID, final String CHANNEL_NAME, final String CHANNEL_DESCRIPTION) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Check Permission
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        NOTIFICATION_CODE);
            }

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);
//            channel.setSound(soundUri, audioAttributes);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void createNotificationRemindWork() {
        final String CHANNEL_ID = "CHANNEL_NOTIFICATION_REMIND_ID";
        final String CHANNEL_NAME = "CHANNEL_NOTIFICATION_REMIND_NAME";
        final String CHANNEL_DESCRIPTION = "CHANNEL_NOTIFICATION_REMIND_DESCRIPTION";
        final String TITLE = "Đã tới giờ tập luyện rồi, hãy vào để tập luyện nhé !!";
        final String CONTENT = "";

        createNotificationChannel(context, CHANNEL_ID, CHANNEL_NAME, CHANNEL_DESCRIPTION);
        createNotification(context, CHANNEL_ID, TITLE, CONTENT);
    }
}
